package com.upf.nli.analyzer.semantic_analyzer.repository.semantic;

import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Course;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Library;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LibraryRepository extends CrudRepository<Library, Integer> {

    Optional<Library> findByName(String name);

}
