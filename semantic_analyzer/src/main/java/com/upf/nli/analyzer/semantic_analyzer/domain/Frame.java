package com.upf.nli.analyzer.semantic_analyzer.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Frame {

    @Id
    @Column(length = 30)
    private String name;

    @Column(length = 1000)
    private String keyword;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return name.equals(frame.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
