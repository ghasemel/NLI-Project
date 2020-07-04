package com.upf.nli.analyzer.semantic_analyzer.repository;

import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrameRepository extends CrudRepository<Frame, String> {
    List<Frame> findAllByKeywordContaining(String keyword);
}
