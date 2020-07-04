package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.domain.Template;
import com.upf.nli.analyzer.semantic_analyzer.entity.AnswerEntity;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;

import java.util.Optional;

public interface TemplateService {
    Optional<Template> getTemplate(QueryEntity query, AnswerEntity answer);
    Optional<String> getResult(Template template, AnswerEntity answer);
}
