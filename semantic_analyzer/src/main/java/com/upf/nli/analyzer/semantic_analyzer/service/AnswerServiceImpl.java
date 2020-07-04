package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.entity.AnswerEntity;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import com.upf.nli.analyzer.semantic_analyzer.enums.SemanticFrame;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.CourseRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.LibraryRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.ProgrammeRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.TeacherRepository;
import com.upf.nli.analyzer.semantic_analyzer.service.answer.*;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final CourseRepository courseRepository;
    private final LibraryRepository libraryRepository;
    private final ProgrammeRepository programmeRepository;
    private final TeacherRepository teacherRepository;

    public AnswerServiceImpl(CourseRepository courseRepository, LibraryRepository libraryRepository, ProgrammeRepository programmeRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.libraryRepository = libraryRepository;
        this.programmeRepository = programmeRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public AnswerEntity answerTheQuery(QueryEntity query) {
        AnswerEntity answer = new AnswerEntity();

        if (query.getFrame() == null) {
            answer.setNeedFrame(true);
            return answer;
        }

        if (query.getQuestion() == null) {
            answer.setNeedQuestion(true);
            return answer;
        }

        if (query.getInfo() == null) {
            answer.setNeedInfo(true);
            return answer;
        }

        Answer answerObject = createAnswerObject(query.getFrame().getName());
        answer = answerObject.runQuery(query);

        return answer;
    }

    private Answer createAnswerObject(String frameName) {
        switch (SemanticFrame.getByName(frameName)) {
            case Course:
                return new CourseAnswer(courseRepository);

            case Library:
                return new LibraryAnswer(libraryRepository);

            case Teacher:
                return new TeacherAnswer(teacherRepository, courseRepository);

            case Programme:
                return new ProgrammeAnswer(programmeRepository);
        }
        throw new RuntimeException("semantic frame is not defined: " + frameName);
    }
}
