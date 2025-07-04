package com.black_opeartive.schat;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvironmentLoader {
    public static void load() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(
            dotenvEntry -> {
                System.setProperty(
                        dotenvEntry.getKey(),
                        dotenvEntry.getValue()
                );
            }
        );
    }
}
