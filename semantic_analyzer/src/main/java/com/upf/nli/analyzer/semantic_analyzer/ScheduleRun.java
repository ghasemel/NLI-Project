package com.upf.nli.analyzer.semantic_analyzer;


import com.upf.nli.analyzer.semantic_analyzer.domain.*;
import com.upf.nli.analyzer.semantic_analyzer.entity.AnswerEntity;
import com.upf.nli.analyzer.semantic_analyzer.entity.GreetingEntity;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import com.upf.nli.analyzer.semantic_analyzer.exception.SemanticFrameDoesNotExist;
import com.upf.nli.analyzer.semantic_analyzer.service.*;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Component
@Slf4j
public class ScheduleRun {

    private final InputBufferService inputService;
    private final FrameService frameService;
    private final QueryService queryService;
    private final AnswerService answerService;
    private final TemplateService templateService;
    private final ErrorService errorService;
    private final OutputService outputService;

    private Stack<Frame> history;
    private Stack<QueryEntity> queries;


    public ScheduleRun(InputBufferService inputService, FrameService frameService, QueryService queryService, AnswerService answerService, TemplateService templateService, ErrorService errorService, OutputService outputService) {
        this.inputService = inputService;
        this.frameService = frameService;
        this.queryService = queryService;
        this.answerService = answerService;
        this.templateService = templateService;
        this.errorService = errorService;
        this.outputService = outputService;
        this.history = new Stack<>();
        this.queries = new Stack<>();

    }


    @Scheduled(fixedRate = 1000)
    public void runApp() throws IOException, ParseException {
        //log.info("schedule triggered");

        try {

            Frame currentFrame = null;

            // check input
            if (!inputService.isThereAnyInput())
                return;

            SpacySentence sentence = inputService.readInputFile();
            if (sentence == null || sentence.count() == 0)
                return;

            Optional<String> greeting = GreetingEntity.checkForTheGreeting(sentence);
            if (greeting.isPresent()) {
                log.info(greeting.get());
                outputService.writeToOutput(greeting.get());
                return;
            }

            Optional<Spacy> root = sentence.getRoot();
            if (root.isPresent()) {
                List<Frame> frameList = frameService.getByKeyword(root.get());

                if (!frameList.isEmpty()) {
                    currentFrame = frameList.get(0);
                }
            }

            if (currentFrame == null) {
                List<Spacy> nones = sentence.getNones();
                if (nones != null && !nones.isEmpty()) {
                    for (Spacy none : nones) {
                        List<Frame> frameList = frameService.getByKeyword(none);
                        if (!frameList.isEmpty()) {
                            currentFrame = frameList.get(0);
                            break;
                        }
                    }
                }
            }

            if (currentFrame != null) {
                //if (!history.isEmpty() && !currentFrame.equals(history.peek())) {
                    history.clear();
                    queries.clear();
                //}
                history.add(currentFrame);
            }

            if (history.isEmpty())
                throw new SemanticFrameDoesNotExist();


            // find the question
            Optional<QueryEntity> query;
            query = queryService.processSentence(sentence, history);
            if (query.isPresent()) {
                // if there is not info check for the previous info
                if (query.get().isNeedInfo() && query.get().getInfo().isEmpty() && !queries.isEmpty() && !queries.peek().getInfo().isEmpty())
                    query.get().addInfo(queries.peek().getInfo().get(0));

                queries.push(query.get());
                log.info(query.get().toString());
            }


            // answer
            if (query.isPresent()) {
                AnswerEntity answer = answerService.answerTheQuery(query.get());
                log.info(answer.toString());

                Optional<Template> template = templateService.getTemplate(query.get(), answer);
                if (template.isPresent()) {
                    Optional<String> result = templateService.getResult(template.get(), answer);
                    if (result.isPresent()) {
                        log.info(result.get());
                        outputService.writeToOutput(result.get());
                    }
                }
            }
        } catch (RuntimeException rex) {
            log.warn(rex.toString());

            String message = errorService.processException(rex);
            log.error(message);
            outputService.writeToOutput(message);

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
