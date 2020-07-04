package com.upf.nli.analyzer.semantic_analyzer.repository;


import com.upf.nli.analyzer.semantic_analyzer.domain.Attribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeRepository extends CrudRepository<Attribute, Integer> {
    List<Attribute> findAll();
    //List<Attribute> findAllByFrame_NameIn(Collection<String> frameNames);
    //List<Attribute> findAllByFrame(Frame frame);
    //Optional<Attribute> findByKeywordContainingAndFrame(String keyword, Frame frame);
    //List<Attribute> findAllByKeywordContainingAndFrame(String keyword, Frame frame);
}
