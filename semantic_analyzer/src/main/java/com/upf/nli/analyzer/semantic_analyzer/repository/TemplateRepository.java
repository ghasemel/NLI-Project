package com.upf.nli.analyzer.semantic_analyzer.repository;

import com.upf.nli.analyzer.semantic_analyzer.domain.Attribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import com.upf.nli.analyzer.semantic_analyzer.domain.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Integer> {
    List<Template> findByFrameAndAttribute(Frame frame, Attribute attribute);
}
