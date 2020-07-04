package com.upf.nli.analyzer.semantic_analyzer.repository;

import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import com.upf.nli.analyzer.semantic_analyzer.domain.FrameAttribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface FrameAttributeRepository extends CrudRepository<FrameAttribute, Integer> {
    List<FrameAttribute> findByFrameAndSearchForValueTrue(Frame frame);
    List<FrameAttribute> findAllByFrame_NameIn(Collection<String> frameNames);
    List<FrameAttribute> findAllByFrameAndAttribute_KeywordContaining(Frame frame, String keyword);
    List<FrameAttribute> findAllByFrame(Frame frame);

    //Optional<FrameAttribute> findByFrameAndAttribute_KeywordContaining(Frame frame, String keyword);
    //Optional<FrameAttribute> findByFrameAndGroupValueIsNotNullAndGroupValue_SubValues_Val(Frame frame, String value);
}
