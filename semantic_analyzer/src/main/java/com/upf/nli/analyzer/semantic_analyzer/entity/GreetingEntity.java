package com.upf.nli.analyzer.semantic_analyzer.entity;

import com.upf.nli.analyzer.semantic_analyzer.domain.Spacy;
import com.upf.nli.analyzer.semantic_analyzer.domain.SpacySentence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class GreetingEntity {
    private String question;
    private String answer;

    private static List<GreetingEntity> greeting;

    static {
        greeting = new ArrayList<>();

        greeting.add(new GreetingEntity("hello", "hello my friend."));
        greeting.add(new GreetingEntity("your name", "my name is marbin."));
        greeting.add(new GreetingEntity("thank", "you are welcome!"));
        greeting.add(new GreetingEntity("how are you", "fine thanks!"));
        greeting.add(new GreetingEntity("tired", "Yes, please disconnect me!"));
    }

    public static Optional<String> checkForTheGreeting(SpacySentence sentence) {
        StringBuilder lineSb = new StringBuilder();
        for (Spacy spacy : sentence.getSpacyList()) {
            if (spacy.getText().equals("new_line") || spacy.getText().equals("")) {
                continue;
            }
            lineSb.append(spacy.getText()).append(" ");
        }

        String l = lineSb.toString().trim();
        Optional<GreetingEntity> first = greeting.stream().filter(g -> l.contains(g.question)).findFirst();

        return first.map(greetingEntity -> greetingEntity.answer);
    }
}
