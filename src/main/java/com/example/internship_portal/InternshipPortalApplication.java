package com.example.internship_portal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.example.internship_portal","com.example.internship_portal.dto"})
@EnableJpaRepositories(basePackages = "com.example.internship_portal.repo")
@EntityScan(basePackages = "com.example.internship_portal.model")
public class InternshipPortalApplication {
	public static void main(String[] args) {
		SpringApplication.run(InternshipPortalApplication.class ,args);
	}
}
