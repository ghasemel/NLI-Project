package com.upf.nli.analyzer.semantic_analyzer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 200)
    private String sentence;

    @Column(length = 50)
    private String keyword;

    @OneToOne
    private Frame frame;

    @OneToOne
    private Attribute attribute;


}
