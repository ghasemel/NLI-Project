package com.upf.nli.analyzer.semantic_analyzer.domain.semantic;


import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Course {

    public static final String COL_NAME = "name";
    public static final String COL_START = "start";
    public static final String COL_END = "end";
    public static final String COL_TEACHER = "teacher";
    public static final String COL_TIME = "classtime";
    public static final String COL_CREDIT = "credit";
    public static final String COL_PROGRAMME = "programme";
    public static final String COL_LANG = "language";
    public static final String COL_SUMMARY = "summary";
    public static final String COL_SYLLABUS = "syllabus";
    public static final String COL_CLASSROOM = "classroom";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Frame frame;

    @ManyToOne
    private Teacher teacher;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String start;

    @Column(nullable = false, length = 100)
    private String end;

    @Column(length = 20, nullable = false)
    private String classroom;

    @Column(nullable = false, length = 100)
    private String classtime;

    @Column(nullable = false)
    private int credit;

    @Column(length = 300)
    private String syllabus;

    @ManyToOne
    private Programme programme;

    @Column(length = 50, nullable = false)
    private String language;

    @Column(length = 1000, nullable = false)
    private String summary;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
