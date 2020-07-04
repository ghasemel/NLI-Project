package com.upf.nli.analyzer.semantic_analyzer.domain.semantic;


import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Programme {

    public static final String COL_NAME = "name";
    public static final String COL_COURSE = "course";
    public static final String COL_PRICE = "price";
    public static final String COL_CONTENT = "content";
    public static final String COL_LANG = "language";
    public static final String COL_DIRECTOR = "director";
    public static final String COL_DURATION = "duration";
    public static final String COL_DEGREE = "degree";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Frame frame;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private float price;

    @OneToMany(mappedBy = "programme")
    private List<Course> courses;

    @Column(length = 2000)
    private String content;

    @Column(length = 50, nullable = false)
    private String language;

    @Column(length = 100, nullable = false)
    private String director;

    @Column(nullable = false)
    private int duration;

    @Column(length = 50)
    private String degree;

    @Override
    public String toString() {
        return "Programme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programme programme = (Programme) o;
        return id == programme.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
