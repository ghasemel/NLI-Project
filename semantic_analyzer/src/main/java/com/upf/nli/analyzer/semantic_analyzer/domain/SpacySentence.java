package com.upf.nli.analyzer.semantic_analyzer.domain;

import com.upf.nli.analyzer.semantic_analyzer.service.AttributeService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class SpacySentence {

    @Getter
    private List<Spacy> spacyList;

    public SpacySentence() {
        spacyList = new ArrayList<>();
    }

    public void addWord(Spacy s) {
        spacyList.add(s);
    }

    public int count() {
        return spacyList.size();
    }

    public Optional<Spacy> getRoot() {
        return spacyList.stream().filter(s -> s.getDependency().equals("root")).findFirst();
    }

    public List<Spacy> getNones() {
        return spacyList.stream().filter(s -> s.getPos().equals("noun") || s.getPos().equals("propn")).collect(Collectors.toList());
    }

    public List<Attribute> getAttributes(AttributeService attributeService) {

        // TODO check how many root word could happen in a given sentence,
        // if more than one root may exist in a sentence then
        // we need to change return value to list
        Optional<Spacy> rootOpt = getRoot();

        List<Attribute> attributeList;
        if (rootOpt.isPresent()) {
            attributeList = attributeService.getByName(rootOpt.get().getLemma());
        } else {
            List<Spacy> noneList = getNones();
            attributeList = attributeService.getByFrameNames(noneList);
        }
        return attributeList;
    }
}
