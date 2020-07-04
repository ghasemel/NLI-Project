package com.upf.nli.analyzer.semantic_analyzer.exception;

import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import lombok.Getter;

public class NoProgrammeInfoFound extends RuntimeException {
    @Getter
    private final QueryEntity query;

    public NoProgrammeInfoFound(QueryEntity query) {
        super("there is no information for programme");
        this.query = query;
    }

    @Override
    public String toString() {
        return "NoProgrammeInfoFound{" +
                "query=" + query + '\'' +
                ", message=" + super.getMessage() +
                '}';
    }
}
