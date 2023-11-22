package com.sirma.task.longestprojects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.sirma.task.longestprojects.repository")
public class LongestProjectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LongestProjectsApplication.class, args);
	}

}