package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.domain.Attribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import com.upf.nli.analyzer.semantic_analyzer.domain.Spacy;

import java.util.List;

public interface AttributeService {
    List<Attribute> getByName(String name);
    List<Attribute> getAll();

    List<Attribute> getByFrameNames(List<Spacy> frameNames);

    List<Attribute> getByKeywordAndFrame(Spacy spacy, Frame frame);
}
