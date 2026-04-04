package com.project.dockerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class main {

    @SpringBootApplication
    public class DockerQAPApplication {
        public static void main(String[] args) {
            SpringApplication.run(DockerQAPApplication.class, args);
        }
    }
}
