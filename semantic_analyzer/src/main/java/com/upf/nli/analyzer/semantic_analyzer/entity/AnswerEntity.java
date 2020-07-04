package com.upf.nli.analyzer.semantic_analyzer.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerEntity {
    private boolean needFrame;
    private boolean needQuestion;
    private boolean needInfo;
    private Object var1;
    private Object var2;
}
