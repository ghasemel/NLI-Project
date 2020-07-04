package com.upf.nli.analyzer.semantic_analyzer.service.answer;

import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Course;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Programme;
import com.upf.nli.analyzer.semantic_analyzer.entity.AnswerEntity;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import com.upf.nli.analyzer.semantic_analyzer.exception.NoInfoFound;
import com.upf.nli.analyzer.semantic_analyzer.exception.NoProgrammeInfoFound;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.ProgrammeRepository;

import java.util.Optional;
import java.util.stream.Collectors;


public class ProgrammeAnswer extends Answer {

    private final ProgrammeRepository programmeRepository;

    public ProgrammeAnswer(ProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    }

    @Override
    public AnswerEntity runQuery(QueryEntity query) {

        if (query.getInfo() == null)
            throw new NoInfoFound();

        Optional<Object> programmeObj = query.getInfo().stream().filter(s -> s instanceof Programme).findFirst();
        if (!programmeObj.isPresent())
            throw new NoProgrammeInfoFound(query);

        Programme programme = (Programme) programmeObj.get();

        AnswerEntity answer = new AnswerEntity();
        answer.setVar1(programme.getName());

        switch (query.getQuestion().getName()) {
            case Programme.COL_COURSE:
                answer.setVar2(programme.getCourses().stream().map(Course::getName).collect(Collectors.toList()));
                break;

            case Programme.COL_PRICE:
                answer.setVar2(programme.getPrice());
                break;

            case Programme.COL_CONTENT:
                answer.setVar2(programme.getContent());
                break;

            case Programme.COL_LANG:
                answer.setVar2(programme.getLanguage());
                break;

            case Programme.COL_DIRECTOR:
                answer.setVar2(programme.getDirector());
                break;

            case Programme.COL_DURATION:
                answer.setVar2(programme.getDuration());
                break;

            case Programme.COL_DEGREE:
                answer.setVar2(programme.getDegree());
                break;
        }

        return answer;
    }


}
