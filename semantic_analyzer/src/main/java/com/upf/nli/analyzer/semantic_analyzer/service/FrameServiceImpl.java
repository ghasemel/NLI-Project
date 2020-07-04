package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import com.upf.nli.analyzer.semantic_analyzer.domain.Spacy;
import com.upf.nli.analyzer.semantic_analyzer.repository.FrameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrameServiceImpl implements FrameService {

    private final FrameRepository frameRepository;

    public FrameServiceImpl(FrameRepository frameRepository) {
        this.frameRepository = frameRepository;
    }

    @Override
    public List<Frame> getByKeyword(Spacy spacy) {
        return frameRepository.findAllByKeywordContaining("," + spacy.getLemma() + ",");
    }
}
