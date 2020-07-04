package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.domain.SpacySentence;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public interface InputBufferService {
    boolean isThereAnyInput();
    SpacySentence readInputFile() throws IOException, ParseException;
}
