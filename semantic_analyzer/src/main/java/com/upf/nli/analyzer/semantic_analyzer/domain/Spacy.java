package com.upf.nli.analyzer.semantic_analyzer.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonComponent
@Getter
@Setter
public class Spacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;
    private long idx;
    private String lemma;
    private boolean punctuation;
    private boolean space;
    private String shape;

    // part of speech
    private String pos;

    private String tag;
    private String dependency;


    public void setPunctuation(String punc) {
        punctuation = punc.equals("True");
    }

    public void setSpace(String sp) {
        space = sp.equals("True");
    }

    @Override
    public String toString() {
        return "Spacy{" +
                "lemma='" + lemma + '\'' +
                '}';
    }
}
