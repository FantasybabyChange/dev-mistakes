package com.fantasybaby.dee.code.oom.weakhashmapoom;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dee.code.oom.weakhashmapoom"
        , exclude = { RedissonAutoConfiguration.class, RedisAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class WeakCommonMistakesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeakCommonMistakesApplication.class, args);
    }
}

