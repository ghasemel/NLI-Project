package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.config.AppConfig;
import com.upf.nli.analyzer.semantic_analyzer.domain.Spacy;
import com.upf.nli.analyzer.semantic_analyzer.domain.SpacySentence;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Service
public class InputBufferServiceImpl implements InputBufferService {

    private final AppConfig appConfig;
    private JSONParser parser;


    public InputBufferServiceImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
        parser = new JSONParser();
    }

    private List<String> readFile() throws IOException {
        List<String> lines = new ArrayList<>();
        for (File f : Objects.requireNonNull(new File(appConfig.getInputPath()).listFiles())) {
            try (Scanner scanner = new Scanner(f)) {
                while (scanner.hasNext())
                    lines.add(scanner.nextLine());
            }
            break;
        }


        return lines;
    }

    private Spacy convertJsonToObject(JSONObject json) {
       /* "[{\"text\":\"{0}\"," \
        "\"idx\":\"{1}\"," \
        "\"lemma\":\"{2}\"," \
        "\"is_punct\":\"{3}\"," \
        "\"is_space\":\"{4}\"," \
        "\"shape\":\"{5}\"," \
        "\"pos\":\"{6}\"," \
        "\"tag\":\"{7}\"," \
        "\"dep\":\"{8}\"}]\
        /*JSONArray companyList = (JSONArray) jsonObject.get("Company List");
            System.out.println("\nCompany List:");
            Iterator<String> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        */

        Spacy spacy = new Spacy();
        spacy.setText((String) json.get("text"));
        spacy.setIdx((Long) json.get("idx"));
        spacy.setLemma((String) json.get("lemma"));
        spacy.setPunctuation((String) json.get("is_punct"));
        spacy.setSpace((String) json.get("is_space"));
        spacy.setShape((String) json.get("shape"));
        spacy.setPos((String) json.get("pos"));
        spacy.setTag((String) json.get("tag"));
        spacy.setDependency((String) json.get("dep"));

        return spacy;
    }

    private void backupFile() {
        for (File f : Objects.requireNonNull(new File(appConfig.getInputPath()).listFiles())) {
            f.renameTo(new File(appConfig.getBackupPath() + f.getName()));
        }
    }

    @Override
    public boolean isThereAnyInput() {
        File dir = new File(appConfig.getInputPath());
        if (!dir.exists())
            throw new RuntimeException("input directory " + appConfig.getInputPath() + " not exist");

        return !(dir.listFiles() == null || Objects.requireNonNull(dir.listFiles()).length == 0);
    }

    @Override
    public SpacySentence readInputFile() throws IOException, ParseException {
        SpacySentence sentence = new SpacySentence();

        List<String> lines = readFile();
        for (String line : lines) {
            Object obj = parser.parse(line.toLowerCase());
            JSONObject jsonObject = (JSONObject) obj;
            sentence.addWord(convertJsonToObject(jsonObject));
        }
        backupFile();

        return sentence;
    }
}
