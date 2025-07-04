package com.black_opeartive.schat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchatBackendApplication {
    public static void main(String[] args) {
        EnvironmentLoader.load();
		SpringApplication.run(SchatBackendApplication.class, args);
    }
}
