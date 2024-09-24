package com.example.PoliticalPreparedness.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication (scanBasePackages = "com.example.PoliticalPreparedness")
@EntityScan("com.example.PoliticalPreparedness.models")
@EnableJpaRepositories("com.example.PoliticalPreparedness.repositories")
public class PoliticalPreparednessApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoliticalPreparednessApplication.class, args);
	}

}
