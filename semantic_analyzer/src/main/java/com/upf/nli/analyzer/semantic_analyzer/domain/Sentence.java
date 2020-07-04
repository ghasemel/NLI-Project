package com.upf.nli.analyzer.semantic_analyzer.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false)
    @Type(type = "text")
    private String sentence;

    // TODO private Session session;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime created;


}
