package com.upf.nli.analyzer.semantic_analyzer.enums;

import com.upf.nli.analyzer.semantic_analyzer.config.GV;
import lombok.Getter;

public enum SemanticFrame {
    Course(GV.COURSE),
    Library(GV.LIBRARY),
    Teacher(GV.TEACHER),
    Programme(GV.PROGRAMME);



    @Getter
    private String name;

    SemanticFrame(String name) {
        this.name = name;
    }

    public static SemanticFrame getByName(String name) {
        switch (name) {
            case GV.COURSE: return Course;
            case GV.LIBRARY: return Library;
            case GV.TEACHER: return Teacher;
            case GV.PROGRAMME: return Programme;
        }
        throw new RuntimeException("frame not defined");
    }
}
