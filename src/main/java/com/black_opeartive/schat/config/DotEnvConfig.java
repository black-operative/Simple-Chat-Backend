package com.black_opeartive.schat.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class DotEnvConfig {
    private final Dotenv dotenv = Dotenv.load();

    public String get(String envVariable) {
        return dotenv.get(envVariable);
    }
}
