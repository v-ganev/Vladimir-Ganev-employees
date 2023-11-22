package com.sirma.task.longestprojects.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Configuration
public class CustomConfig {

    @Bean
    public DateTimeFormatter getDateTimeFormatter() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("[dd-MM-yyyy]" + "[MM-dd-yyyy]" + "[yyyy-MM-dd]" + "[yyyy/MM/dd]" + "[yyyy.MM.dd]" + "[yyyy MM dd]"));
        return dateTimeFormatterBuilder.toFormatter();
    }
}
