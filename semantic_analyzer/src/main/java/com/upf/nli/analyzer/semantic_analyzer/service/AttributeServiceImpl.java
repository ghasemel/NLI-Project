package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.domain.Attribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import com.upf.nli.analyzer.semantic_analyzer.domain.FrameAttribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.Spacy;
import com.upf.nli.analyzer.semantic_analyzer.repository.AttributeRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.FrameAttributeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepo;
    private final FrameAttributeRepository mapRepository;

    public AttributeServiceImpl(AttributeRepository attributeRepo, FrameAttributeRepository mapRepository) {
        this.attributeRepo = attributeRepo;
        this.mapRepository = mapRepository;
    }

    @Override
    public List<Attribute> getByName(String name) {
        return null;
    }

    @Override
    public List<Attribute> getAll() {
        return attributeRepo.findAll();
    }

    @Override
    public List<Attribute> getByFrameNames(List<Spacy> frameNames) {
        if (frameNames == null || frameNames.isEmpty())
            return null;

        return mapRepository.findAllByFrame_NameIn(
                frameNames.stream()
                        .map(Spacy::getLemma)
                        .collect(Collectors.toList())
        ).stream()
                .map(FrameAttribute::getAttribute)
                .collect(Collectors.toList());
    }

    @Override
    public List<Attribute> getByKeywordAndFrame(Spacy spacy, Frame frame) {
        return mapRepository
                .findAllByFrameAndAttribute_KeywordContaining(frame, "," + spacy.getLemma() + ",")
                .stream()
                .map(FrameAttribute::getAttribute)
                .collect(Collectors.toList());
    }
}
