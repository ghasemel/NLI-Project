package com.upf.nli.analyzer.semantic_analyzer.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties(AppConfig.class)
public class Beans {

    /*
    @Bean
    public List<Keyword> keywordList(KeywordService keywordService) {
        return keywordService.getAll();
    }

    @Bean
    public List<Attribute> attributeList(AttributeService attributeService) {
        return attributeService.getAll();
    }
     */
}
