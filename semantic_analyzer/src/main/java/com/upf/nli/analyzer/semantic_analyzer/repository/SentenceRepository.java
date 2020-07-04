package com.upf.nli.analyzer.semantic_analyzer.repository;


import com.upf.nli.analyzer.semantic_analyzer.domain.Sentence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentenceRepository extends CrudRepository<Sentence, Integer> {
    
}
