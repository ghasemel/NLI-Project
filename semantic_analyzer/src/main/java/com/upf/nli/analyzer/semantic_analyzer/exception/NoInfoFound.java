package com.upf.nli.analyzer.semantic_analyzer.exception;

public class NoInfoFound extends RuntimeException {
    public NoInfoFound() {
        super("no information has been provided");
    }
}
