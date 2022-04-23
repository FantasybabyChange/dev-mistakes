package com.fantasybaby.dev.error.design.cachedesign.cacheinvalid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.design.cachedesign.cacheinvalid"
        , exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CacheInvalidApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheInvalidApplication.class, args);
    }
}

