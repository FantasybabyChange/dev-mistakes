package com.fantasybaby.dev.error.design.asyncprocess.compensation;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.design.asyncprocess.compensation"
        , exclude = {RedissonAutoConfiguration.class, RedisAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CompenstationMistakesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompenstationMistakesApplication.class, args);
    }
}

