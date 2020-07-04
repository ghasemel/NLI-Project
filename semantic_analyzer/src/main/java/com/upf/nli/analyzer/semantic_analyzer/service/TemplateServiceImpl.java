package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.domain.Template;
import com.upf.nli.analyzer.semantic_analyzer.entity.AnswerEntity;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import com.upf.nli.analyzer.semantic_analyzer.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;

    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public Optional<Template> getTemplate(QueryEntity query, AnswerEntity answer) {
        List<Template> templates = templateRepository.findByFrameAndAttribute(query.getFrame(), query.getQuestion());

        if (templates.size() == 1) {
            return Optional.ofNullable(templates.get(0));

        } else if (templates.size() > 1) {
            int answerParts = answer.getVar1() == null || answer.getVar2() == null ? 1 : 2;

            for (Template t: templates) {
                int missedPart = howManyMissedPart(t);
                if (missedPart == answerParts)
                    return Optional.of(t);
            }
        }

        throw new RuntimeException("there is no template");
    }

    private int howManyMissedPart(Template template) {
        return  template.getVal().split("%").length - 1;
    }

    public Optional<String> getResult(Template template, AnswerEntity answer) {
        int missedPart = howManyMissedPart(template);

        if (missedPart == 2)
            return Optional.ofNullable(String.format(template.getVal(), answer.getVar1(), answer.getVar2()));
        else if (missedPart == 1) {
            if (answer.getVar1() == null)
                return Optional.ofNullable(String.format(template.getVal(), answer.getVar2()));
            return Optional.ofNullable(String.format(template.getVal(), answer.getVar1()));
        }
        return Optional.empty();
    }
}
