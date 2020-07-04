package com.upf.nli.analyzer.semantic_analyzer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Frame frame;

    @ManyToOne
    private Attribute attribute;

    @Column(length = 300)
    private String val;
}
