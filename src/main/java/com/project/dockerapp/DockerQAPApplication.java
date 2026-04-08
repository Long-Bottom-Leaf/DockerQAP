package com.project.dockerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.project.dockerapp.models")
@EnableJpaRepositories(basePackages = "com.project.dockerapp.repository")
public class DockerQAPApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerQAPApplication.class, args);
    }
}