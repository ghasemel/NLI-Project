package com.upf.nli.analyzer.semantic_analyzer.exception;

import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import lombok.Getter;

public class NoLibraryInfoFound extends RuntimeException {
    @Getter
    private final QueryEntity query;

    public NoLibraryInfoFound(QueryEntity query) {
        super("there is no information for library");
        this.query = query;
    }

    @Override
    public String toString() {
        return "NoLibraryInfoFound{" +
                "query=" + query + '\'' +
                ", message=" + super.getMessage() +
                '}';
    }
}
