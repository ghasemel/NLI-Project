package com.upf.nli.analyzer.semantic_analyzer.repository.semantic;

import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Programme;
import com.upf.nli.analyzer.semantic_analyzer.domain.semantic.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProgrammeRepository extends CrudRepository<Programme, Integer> {
    Optional<Programme> findByName(String name);
    Optional<Programme> findByNameContaining(String name);
}
