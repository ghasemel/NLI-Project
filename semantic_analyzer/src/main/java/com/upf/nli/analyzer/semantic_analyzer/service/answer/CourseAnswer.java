package com.upf.nli.analyzer.semantic_analyzer.service.answer;

import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Course;
import com.upf.nli.analyzer.semantic_analyzer.entity.AnswerEntity;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import com.upf.nli.analyzer.semantic_analyzer.exception.NoCourseInfoFound;
import com.upf.nli.analyzer.semantic_analyzer.exception.NoInfoFound;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.CourseRepository;

import java.util.Optional;


public class CourseAnswer extends Answer {

    private final CourseRepository courseRepository;

    public CourseAnswer(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public AnswerEntity runQuery(QueryEntity query) {
        if (query.getInfo() == null)
            throw new NoInfoFound();

        Optional<Object> courseObj = query.getInfo().stream().filter(s -> s instanceof Course).findFirst();
        if (!courseObj.isPresent())
            throw new NoCourseInfoFound(query);

        Course course = (Course) courseObj.get();

        AnswerEntity answer = new AnswerEntity();
        answer.setVar1(course.getName());

        switch (query.getQuestion().getName()) {
            case Course.COL_START:
                answer.setVar2(course.getStart());
                break;

            case Course.COL_END:
                answer.setVar2(course.getEnd());
                break;

            case Course.COL_TEACHER:
                answer.setVar1(null);
                answer.setVar2(course.getTeacher().getName());
                break;

            case Course.COL_TIME:
                answer.setVar2(course.getClasstime());
                break;

            case Course.COL_CLASSROOM:
                answer.setVar1(null);
                answer.setVar2(course.getClassroom());
                break;

            case Course.COL_CREDIT:
                answer.setVar2(course.getCredit());
                break;

            case Course.COL_PROGRAMME:
                answer.setVar2(course.getProgramme().getName());
                break;

            case Course.COL_LANG:
                answer.setVar2(course.getLanguage());
                break;

            case Course.COL_SYLLABUS:
                answer.setVar2(course.getSyllabus());
                break;

            case Course.COL_SUMMARY:
                answer.setVar2(course.getSummary());
                break;
        }

        return answer;
    }


}
