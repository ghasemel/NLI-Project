package com.upf.nli.analyzer.semantic_analyzer.domain.semantic;


import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Teacher {

    public static final String COL_NAME = "person_name";
    public static final String COL_COURSE = "course";
    public static final String COL_LOCATION = "officelocation";
    public static final String COL_EMAIL = "email";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Frame frame;

    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @Column(length = 200, nullable = false)
    private String officeLocation;

    @Column(length = 100)
    private String email;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
