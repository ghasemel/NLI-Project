package com.upf.nli.analyzer.semantic_analyzer.service.answer;

import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Course;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Library;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher;
import com.upf.nli.analyzer.semantic_analyzer.entity.AnswerEntity;
import com.upf.nli.analyzer.semantic_analyzer.entity.QueryEntity;
import com.upf.nli.analyzer.semantic_analyzer.exception.NoLibraryInfoFound;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.LibraryRepository;
import com.upf.nli.analyzer.semantic_analyzer.repository.semantic.TeacherRepository;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;


public class LibraryAnswer extends Answer {
    private final String NAME = "name";
    private final String START = "start";
    private final String END = "end";
    private final String SERVICES = "services";
    private final String LOCATION = "location";

    private final LibraryRepository libraryRepository;

    public LibraryAnswer(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public AnswerEntity runQuery(QueryEntity query) {
        AnswerEntity answer = new AnswerEntity();

        Iterable<Library> all = libraryRepository.findAll();
        Iterator<Library> iterator = all.iterator();

        if (!iterator.hasNext()) {
            throw new NoLibraryInfoFound(query);
        }

        switch (query.getQuestion().getName()) {
            case START:
                answer.setVar2(iterator.next().getStart());
                break;

            case END:
                answer.setVar2(iterator.next().getEnd());
                break;

            case SERVICES:
                answer.setVar2(iterator.next().getServices());
                break;

            case LOCATION:
                answer.setVar2(iterator.next().getLocation());
                break;
        }

        return answer;
    }


}
