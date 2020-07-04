package com.upf.nli.analyzer.semantic_analyzer.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Attribute {

    @Id
    @Column(length = 50)
    private String name;

    @Column(length = 1000)
    private String keyword;

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }


}
