package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import com.upf.nli.analyzer.semantic_analyzer.domain.Spacy;

import java.util.List;

public interface FrameService {
    List<Frame> getByKeyword(Spacy spacy);
}
