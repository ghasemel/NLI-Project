package com.upf.nli.analyzer.semantic_analyzer.repository.semantic;

import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Course;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    Optional<Course> findByName(String name);
    Optional<Course> findByNameContaining(String name);
    List<Course> findAllByTeacher(Teacher teacher);

}
