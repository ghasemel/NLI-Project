package com.upf.nli.analyzer.semantic_analyzer.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app-config")
public class AppConfig {

    private String inputPath;
    private String outputPath;
    private String backupPath;
}
