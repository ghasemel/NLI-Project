package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import com.upf.nli.analyzer.semantic_analyzer.domain.FrameAttribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.SpacySentence;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import com.upf.nli.analyzer.semantic_analyzer.enums.SemanticFrame;
import com.upf.nli.analyzer.semantic_analyzer.repository.AttributeRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.FrameAttributeRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.CourseRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.ProgrammeRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.TeacherRepository;
import com.upf.nli.analyzer.semantic_analyzer.service.query.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Service
public class QueryServiceImpl implements QueryService {

    private final AttributeRepository attributeRepository;
    private final FrameAttributeRepository mapRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final ProgrammeRepository programmeRepository;

    public QueryServiceImpl(AttributeRepository attributeRepository, FrameAttributeRepository mapRepository, CourseRepository courseRepository, TeacherRepository teacherRepository, ProgrammeRepository programmeRepository) {
        this.attributeRepository = attributeRepository;
        this.mapRepository = mapRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.programmeRepository = programmeRepository;
    }

    @Override
    public Optional<QueryEntity> processSentence(SpacySentence sentence, Stack<Frame> history) {

        if (history.isEmpty())
            return Optional.empty();

        Frame lastFrame = history.peek();
        List<FrameAttribute> frameAttributeList = mapRepository.findAllByFrame(lastFrame);
        if (frameAttributeList.isEmpty())
            throw new RuntimeException("there is no attribute for frame " + lastFrame.getName());

        Query query = factory(SemanticFrame.getByName(lastFrame.getName()));
        return query.processAttribute(sentence, frameAttributeList);
    }

    private Query factory(SemanticFrame semanticFrame) {
        switch (semanticFrame) {
            case Course:
                return new CourseQuery(mapRepository, courseRepository);

            case Library:
                return new LibraryQuery(mapRepository);

            case Teacher:
                return new TeacherQuery(mapRepository, teacherRepository, courseRepository);

            case Programme:
                return new ProgrammeQuery(mapRepository, programmeRepository);
        }
        throw new RuntimeException("semantic frame is not defined: " + semanticFrame.getName());
    }

}
