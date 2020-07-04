package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.domain.Error;
import com.upf.nli.analyzer.semantic_analyzer.exception.*;
import com.upf.nli.analyzer.semantic_analyzer.repository.ErrorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ErrorServiceImpl implements ErrorService {
    private static final String NO_FRAME = "NO_FRAME";
    private static final String NO_QUESTION = "NO_QUESTION";
    private static final String NO_INFO = "NO_INFO";
    private static final String NO_COURSE_INFO = "NO_COURSE_INFO";
    private static final String NO_TEACHER_INFO = "NO_TEACHER_INFO";
    private static final String NO_PROGRAMME_INFO = "NO_PROGRAMME_INFO";
    private static final String NO_LIBRARY_INFO = "NO_LIBRARY_INFO";

    private final ErrorRepository errorRepository;

    public ErrorServiceImpl(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    public String processException(RuntimeException ex) {

        Optional<Error> error = Optional.empty();
        if (ex instanceof SemanticFrameDoesNotExist) {
            error = errorRepository.findByKeyword(NO_FRAME);

        } else if (ex instanceof NoQuestionFound) {
            error = errorRepository.findByKeyword(NO_QUESTION);

        } else if (ex instanceof NoInfoFound) {
            error = errorRepository.findByKeyword(NO_INFO);

        } else if (ex instanceof NoCourseInfoFound) {
            error = errorRepository.findByKeyword(NO_COURSE_INFO);
            //log.error(((NoCourseInfoFound)ex).getQuery().toString());

        } else if (ex instanceof NoTeacherInfoFound) {
            error = errorRepository.findByKeyword(NO_TEACHER_INFO);
            //log.error(((NoTeacherInfoFound)ex).getQuery().toString());

        } else if (ex instanceof NoProgrammeInfoFound) {
            error = errorRepository.findByKeyword(NO_PROGRAMME_INFO);
            //log.error(((NoProgrammeInfoFound)ex).getQuery().toString());

        } else if (ex instanceof NoLibraryInfoFound) {
            error = errorRepository.findByKeyword(NO_LIBRARY_INFO);
            //log.error(((NoLibraryInfoFound)ex).getQuery().toString());
        }

        if (error.isPresent())
            return error.get().getSentence();

        return ex.getMessage();
    }
}
