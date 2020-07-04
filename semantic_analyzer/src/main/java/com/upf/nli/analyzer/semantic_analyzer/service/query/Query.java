package com.upf.nli.analyzer.semantic_analyzer.service.query;

import com.upf.nli.analyzer.semantic_analyzer.domain.*;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import com.upf.nli.analyzer.semantic_analyzer.exception.NoQuestionFound;
import com.upf.nli.analyzer.semantic_analyzer.repository.FrameAttributeRepository;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Query {
    @Getter
    private final FrameAttributeRepository mapRepository;


    //protected abstract Optional<Query> processAttribute(SpacySentence sentence, List<FrameAttributeValue> allFrameInfo);
    protected abstract boolean isThatDesiredValue(String keyword);
    protected abstract boolean isInformationPartNeeded();
    protected abstract boolean checkForInformation();
    protected abstract Optional<Object> getRecordByAttributeAndFilter(Attribute question, FrameAttribute searchableAttr, String filter);
    private List<String> QuestionWord;

    protected Query(FrameAttributeRepository mapRepository) {
        this.mapRepository = mapRepository;
        QuestionWord = new ArrayList<>();
        QuestionWord.add("what");
        QuestionWord.add("which");
    }

    protected boolean isItQuestion(Spacy spacy) {
        return !spacy.getDependency().equals("compound");
    }

    protected boolean shouldIgnoreSpacy(Spacy spacy) {
        return spacy.getDependency().equals("prep") ||
                spacy.getDependency().equals("det") ||
                spacy.getPos().equals("aux");
    }

    /*protected Optional<FrameAttribute> getPossibleQuestion(Spacy spacy, FrameAttributeRepository mapRepository, Frame frame) {
        List<FrameAttribute> allAttrs = mapRepository.findAllByFrameAndAttribute_KeywordContaining(frame, "," + spacy.getLemma().trim() + ",");

        if (allAttrs.size() > 1)
            throw new RuntimeException("more than one attribute have same keyword: " + spacy.getLemma());

        if (allAttrs.size() == 1)
            return Optional.of(allAttrs.get(0));

        return Optional.empty();
    }*/



    public Optional<QueryEntity> processAttribute(SpacySentence sentence, List<FrameAttribute> allFrameInfo) {
        Frame frame = allFrameInfo.get(0).getFrame();

        QueryEntity q = new QueryEntity();
        q.setNeedInfo(isInformationPartNeeded());
        q.setFrame(frame);

        boolean questionWordFlag = false;
        for (Spacy spacy : sentence.getSpacyList()) {

            if (shouldIgnoreSpacy(spacy))
                continue;

            if (QuestionWord.contains(spacy.getLemma()))
                questionWordFlag = true;

            Optional<Attribute> questionPart = getQuestionPart(spacy, frame);
            if ((questionPart.isPresent() && q.getQuestion() == null)) {
                q.setQuestion(questionPart.get());
                break;

            } else if (questionWordFlag || spacy.getTag().contains("nn")) {

            }
        }

        if (q.getQuestion() == null)
            throw new NoQuestionFound(q);

        for (Spacy spacy : sentence.getSpacyList()) {
            if (!spacy.getTag().contains("nn"))
                continue;

            // find by keyword
            if (checkForInformation()) {
                Optional<Object> infoPart = getInfoPart(spacy, frame, q.getQuestion());
                if (infoPart.isPresent()) {
                    q.addInfo(infoPart.get());
                    break;
                }

            }
        }

        return Optional.of(q);
    }

    protected Optional<Attribute> getQuestionPart(Spacy spacy, Frame frame) {
        if (!isItQuestion(spacy))
            return Optional.empty();

        List<FrameAttribute> allAttrs = mapRepository.findAllByFrameAndAttribute_KeywordContaining(frame, "," + spacy.getLemma() + ",");

        if (allAttrs.size() > 1)
            throw new RuntimeException("more than one attributes have same keyword: " + spacy.getLemma());

        if (allAttrs.size() == 1)
            return Optional.of(allAttrs.get(0).getAttribute());

        return Optional.empty();
    }

    protected Optional<Object> getInfoPart(Spacy spacy, Frame frame, Attribute question) {
        // find all searchable attributes by the frame
        List<FrameAttribute> searchableAttribs = mapRepository.findByFrameAndSearchForValueTrue(frame);
        if (searchableAttribs.isEmpty())
            return Optional.empty();

        for (FrameAttribute a : searchableAttribs) {
            if (isThatDesiredValue(a.getAttribute().getKeyword())) {
                Optional<Object> record = getRecordByAttributeAndFilter(question, a, spacy.getText());
                if (record.isPresent())
                    return record;
            }
        }

        return Optional.empty();
    }
}
