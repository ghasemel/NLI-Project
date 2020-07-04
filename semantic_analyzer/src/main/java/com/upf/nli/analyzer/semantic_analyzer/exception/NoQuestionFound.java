package com.upf.nli.analyzer.semantic_analyzer.exception;

import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import lombok.Getter;

public class NoQuestionFound extends RuntimeException {
    @Getter
    private final QueryEntity query;


    public NoQuestionFound(QueryEntity query) {
        super("there is no question in the sentence");
        this.query = query;
    }

    @Override
    public String toString() {
        return "NoQuestionFound{" +
                "query=" + query + '\'' +
                ", message=" + super.getMessage() +
                '}';
    }
}
