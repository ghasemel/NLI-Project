package com.upf.nli.analyzer.semantic_analyzer.service.query;

import com.upf.nli.analyzer.semantic_analyzer.domain.Attribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.FrameAttribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Course;
import com.upf.nli.analyzer.semantic_analyzer.repository.FrameAttributeRepository;

import java.util.Optional;


public class LibraryQuery extends Query {


    public LibraryQuery(FrameAttributeRepository mapRepository) {

        super(mapRepository);
    }


    @Override
    protected boolean isThatDesiredValue(String keyword) {
        return false;
    }

    @Override
    protected boolean checkForInformation() {
        return false;
    }

    @Override
    protected Optional<Object> getRecordByAttributeAndFilter(Attribute question, FrameAttribute searchableAttr, String filter) {
        return Optional.empty();
    }

    @Override
    protected boolean isInformationPartNeeded() {
        return false;
    }
}
