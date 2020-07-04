package com.upf.nli.analyzer.semantic_analyzer.entity;

import com.upf.nli.analyzer.semantic_analyzer.domain.Attribute;
import com.upf.nli.analyzer.semantic_analyzer.domain.Frame;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class QueryEntity {
    private boolean needInfo;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private List<Object> info;

    private Attribute question;
    private Frame frame;

    public QueryEntity() {
        info = new ArrayList<>();
    }

    public void addInfo(Object obj) {
        if (!info.contains(obj))
            info.add(obj);
    }

    /*public void addInfoRange(List<Object> objList) {
        for (Object o : ob)
    }*/
}
