package com.upf.nli.analyzer.semantic_analyzer.service;

import com.upf.nli.analyzer.semantic_analyzer.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Service
@Slf4j
public class OutputServiceImpl implements OutputService {

    private final AppConfig config;

    public OutputServiceImpl(AppConfig config) {
        this.config = config;
    }

    private String getNewFileName() {
        return config.getOutputPath() + "/" + getFileAsDate()  + ".txt";
    }

    private String getFileAsDate() {
        //2019-12-13T17:46:06.250.txt
        return LocalDateTime.now().toString()
                .replace(":", "")
                .replace(".", "");
    }

    @Override
    public void writeToOutput(String value) {
        try {

            PrintWriter writer = new PrintWriter(getNewFileName(), "UTF-8");
            writer.print(value);
            writer.close();

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }
}
