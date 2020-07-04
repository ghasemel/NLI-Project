package com.upf.nli.analyzer.semantic_analyzer.service.query;

import com.upf.nli.analyzer.semantic_analyzer.domain.Attribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.FrameAttribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Course;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher;
import com.upf.nli.analyzer.semantic_analyzer.repository.FrameAttributeRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.CourseRepository;

import java.util.Optional;

import static com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher.COL_NAME;


public class CourseQuery extends Query {
    //private final String SEARCHABLE_COLUMN1_NAME = "name";

    private final CourseRepository courseRepository;

    public CourseQuery(FrameAttributeRepository mapRepository, CourseRepository courseRepository) {
        super(mapRepository);
        this.courseRepository = courseRepository;
    }

    @Override
    protected boolean isThatDesiredValue(String keyword) {
        return keyword.contains(Course.COL_NAME);
    }

    @Override
    protected boolean checkForInformation() {
        return true;
    }

    @Override
    protected Optional<Object> getRecordByAttributeAndFilter(Attribute question, FrameAttribute searchableAttr, String filter) {
        if (question != null) {
           /* if (question.getName().equals(COL_NAME)) {
                Optional<Course> course = courseRepository.findByNameContaining(filter);
                if (course.isPresent())
                    return Optional.of(course.get());*/

            //} else { //if (question.getName().equals(COL_COURSE)) {
            Optional<Course> course = courseRepository.findByNameContaining(filter);
            if (course.isPresent())
                return Optional.of(course.get());
           // }
        }

        /*switch (searchableAttr.getAttribute().getName()) {
            case Course.COL_NAME:
                Optional<Course> course = courseRepository.findByNameContaining(filter);
                if (course.isPresent())
                    return Optional.of(course.get());
        }*/

        return Optional.empty();
    }

    @Override
    protected boolean isInformationPartNeeded() {
        return true;
    }
}
