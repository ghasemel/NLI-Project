package com.upf.nli.analyzer.semantic_analyzer.exception;

public class SemanticFrameDoesNotExist extends RuntimeException {
    public SemanticFrameDoesNotExist() {
        super("no frame was found");
    }
}
