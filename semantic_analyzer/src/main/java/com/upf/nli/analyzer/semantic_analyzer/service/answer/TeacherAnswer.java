package com.upf.nli.analyzer.semantic_analyzer.service.answer;

import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Course;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher;
import com.upf.nli.analyzer.semantic_analyzer.entity.AnswerEntity;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import com.upf.nli.analyzer.semantic_analyzer.exception.NoInfoFound;
import com.upf.nli.analyzer.semantic_analyzer.exception.NoTeacherInfoFound;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.CourseRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.TeacherRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher.*;


public class TeacherAnswer extends Answer {


    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherAnswer(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public AnswerEntity runQuery(QueryEntity query) {
        if (query.getInfo() == null)
            throw new NoInfoFound();

        AnswerEntity answer = new AnswerEntity();

        switch (query.getQuestion().getName()) {
            case COL_NAME:
                Optional<Object> courseObj = query.getInfo().stream().filter(s -> s instanceof Course).findFirst();
                if (courseObj.isPresent()) {
                    Course course = (Course) courseObj.get();
                    answer.setVar1(course.getTeacher().getName());
                    answer.setVar2(course.getName());
                } else {
                    throw new NoTeacherInfoFound(query);
                }
                break;

            case COL_COURSE:
                Optional<Object> infoObj3 = query.getInfo().stream().findFirst();
                if (infoObj3.isPresent()) {
                    Teacher teacher = null;

                    if (infoObj3.get() instanceof Teacher) {
                        teacher = (Teacher) infoObj3.get();

                    } else if (infoObj3.get() instanceof Course) {
                        Course course = (Course) infoObj3.get();
                        teacher = course.getTeacher();
                    }

                    if (teacher != null) {
                        answer.setVar1(teacher.getName());

                        List<Course> courses = courseRepository.findAllByTeacher(teacher);
                        answer.setVar2(courses.stream()
                                .map(Course::getName)
                                .collect(Collectors.toList()));
                    }
                }
                break;

            case COL_LOCATION:
                Optional<Object> infoObj2 = query.getInfo().stream().findFirst();
                if (infoObj2.isPresent()) {
                    if (infoObj2.get() instanceof Teacher) {
                        Teacher teacher = (Teacher) infoObj2.get();
                        answer.setVar1(teacher.getName());
                        answer.setVar2(teacher.getOfficeLocation());
                    } else if (infoObj2.get() instanceof Course) {
                        Course course = (Course) infoObj2.get();
                        answer.setVar1(course.getTeacher().getName());
                        answer.setVar2(course.getTeacher().getOfficeLocation());
                    }
                }
                break;

            case COL_EMAIL:
                Optional<Object> infoObj = query.getInfo().stream().findFirst();
                if (infoObj.isPresent()) {
                    if (infoObj.get() instanceof Teacher) {
                        Teacher teacher = (Teacher) infoObj.get();
                        answer.setVar1(teacher.getName());
                        answer.setVar2(teacher.getEmail());
                    } else if (infoObj.get() instanceof Course) {
                        Course course = (Course) infoObj.get();
                        answer.setVar1(course.getTeacher().getName());
                        answer.setVar2(course.getTeacher().getEmail());
                    }
                }
                break;
        }

        return answer;
    }


}
