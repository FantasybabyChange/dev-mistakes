package com.fantasybaby.dev.error.design.asyncprocess.deadletter;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.design.asyncprocess.deadletter"
        , exclude = {RedissonAutoConfiguration.class, RedisAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class DeadLetterApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeadLetterApplication.class, args);
    }
}

