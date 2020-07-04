package com.upf.nli.analyzer.semantic_analyzer.service.answer;

import com.upf.nli.analyzer.semantic_analyzer.entity.AnswerEntity;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;

public abstract class Answer {
    public abstract AnswerEntity runQuery(QueryEntity queryEntity);
}
