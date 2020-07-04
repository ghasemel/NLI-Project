package com.upf.nli.analyzer.semantic_analyzer.repository;

import com.upf.nli.analyzer.semantic_analyzer.domain.Error;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ErrorRepository extends CrudRepository<Error, Integer> {
    Optional<Error> findByKeyword(String keyword);
}
