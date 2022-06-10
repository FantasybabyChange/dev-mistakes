package com.fantasybaby.dev.error.security.dataandcode.sqlinject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.security.dataandcode.sqlinject")
public class SqlInjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlInjectApplication.class, args);
    }
}

