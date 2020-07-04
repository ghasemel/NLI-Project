package com.upf.nli.analyzer.semantic_analyzer.exception;

import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import lombok.Getter;

public class NoCourseInfoFound extends RuntimeException {
    @Getter
    private final QueryEntity query;

    public NoCourseInfoFound(QueryEntity query) {
        super("there is no information for course");
        this.query = query;
    }

    @Override
    public String toString() {
        return "NoCourseInfoFound{" +
                "query=" + query + '\'' +
                ", message=" + super.getMessage() +
                '}';
    }
}
