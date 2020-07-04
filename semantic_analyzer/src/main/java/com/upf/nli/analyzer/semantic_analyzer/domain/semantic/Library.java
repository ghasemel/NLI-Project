package com.upf.nli.analyzer.semantic_analyzer.domain.semantic;


import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Frame frame;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String location;

    @Column(nullable = false, length = 100)
    private String start;

    @Column(nullable = false, length = 100)
    private String end;

    @Column(nullable = false, length = 300)
    private String services;

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return id == library.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
