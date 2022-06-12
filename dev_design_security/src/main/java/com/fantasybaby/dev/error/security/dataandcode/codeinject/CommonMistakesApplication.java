package com.fantasybaby.dev.error.security.dataandcode.codeinject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;


@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.security.dataandcode.codeinject"
,exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class CommonMistakesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonMistakesApplication.class, args);
    }
}

