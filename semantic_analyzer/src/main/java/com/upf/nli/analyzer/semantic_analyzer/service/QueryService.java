package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import com.upf.nli.analyzer.semantic_analyzer.domain.SpacySentence;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;

import java.util.Optional;
import java.util.Stack;

public interface QueryService {
    Optional<QueryEntity> processSentence(SpacySentence sentence, Stack<Frame> history);
}
