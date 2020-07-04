package com.upf.nli.analyzer.semantic_analyzer.service.query;

import com.upf.nli.analyzer.semantic_analyzer.domain.Attribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.FrameAttribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Programme;
import com.upf.nli.analyzer.semantic_analyzer.repository.FrameAttributeRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.ProgrammeRepository;

import java.util.Optional;


public class ProgrammeQuery extends Query {
    private final String SEARCHABLE_COLUMN1_NAME = "name";

    private final ProgrammeRepository programmeRepository;

    public ProgrammeQuery(FrameAttributeRepository mapRepository, ProgrammeRepository programmeRepository) {
        super(mapRepository);
        this.programmeRepository = programmeRepository;
    }

    @Override
    protected boolean isThatDesiredValue(String keyword) {
        return keyword.contains(SEARCHABLE_COLUMN1_NAME);
    }

    @Override
    protected boolean checkForInformation() {
        return true;
    }

    @Override
    protected Optional<Object> getRecordByAttributeAndFilter(Attribute question, FrameAttribute searchableAttr, String filter) {
        if (question != null) {
            Optional<Programme> programme = programmeRepository.findByNameContaining(filter);
            if (programme.isPresent())
                return Optional.of(programme.get());
        }

       /* switch (searchableAttr.getAttribute().getName()) {
            case SEARCHABLE_COLUMN1_NAME:

        }*/

        return Optional.empty();
    }

    @Override
    protected boolean isInformationPartNeeded() {
        return true;
    }
}
