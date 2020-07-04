package com.upf.nli.analyzer.semantic_analyzer.exception;

import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import lombok.Getter;

public class NoTeacherInfoFound extends RuntimeException {
    @Getter
    private final QueryEntity query;

    public NoTeacherInfoFound(QueryEntity query) {
        super("there is no information for the teacher");
        this.query = query;
    }

    @Override
    public String toString() {
        return "NoTeacherInfoFound{" +
                "query=" + query + '\'' +
                ", message=" + super.getMessage() +
                '}';
    }
}
