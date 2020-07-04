package com.upf.nli.analyzer.semantic_analyzer.repository.semantic;

import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Course;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    Optional<Teacher> findByName(String teacherName);
    Optional<Teacher> findByNameContaining(String teacherName);
    Optional<Teacher> findByCourses_NameContaining(String courseName);
}
