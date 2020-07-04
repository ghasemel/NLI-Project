package com.upf.nli.analyzer.semantic_analyzer.service.query;

import com.upf.nli.analyzer.semantic_analyzer.domain.Attribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.FrameAttribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Course;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher;
import com.upf.nli.analyzer.semantic_analyzer.repository.FrameAttributeRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.CourseRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.TeacherRepository;

import java.util.Optional;

import static com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher.COL_COURSE;
import static com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher.COL_NAME;


public class TeacherQuery extends Query {
    //private final String SEARCHABLE_COLUMN1_NAME = "name";
    //private final String SEARCHABLE_COLUMN2_COURSE = "course";


    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;


    public TeacherQuery(FrameAttributeRepository mapRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        super(mapRepository);
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    protected boolean isThatDesiredValue(String keyword) {
        if (keyword.contains(COL_NAME))
            return true;
        return (keyword.contains(Teacher.COL_COURSE));
    }

    @Override
    protected boolean checkForInformation() {
        return true;
    }

    @Override
    protected Optional<Object> getRecordByAttributeAndFilter(Attribute question, FrameAttribute searchableAttr, String filter) {
        if (question != null) {
            if (question.getName().equals(COL_NAME)) {
                Optional<Course> course = courseRepository.findByNameContaining(filter);
                if (course.isPresent())
                    return Optional.of(course.get());

            } else { //if (question.getName().equals(COL_COURSE)) {
                Optional<Teacher> teacher = teacherRepository.findByNameContaining(filter);
                if (teacher.isPresent())
                    return Optional.of(teacher.get());
            }
        }

        return Optional.empty();
    }

    @Override
    protected boolean isInformationPartNeeded() {
        return true;
    }
}
