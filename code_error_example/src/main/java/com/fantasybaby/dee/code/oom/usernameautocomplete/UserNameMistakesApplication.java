package com.fantasybaby.dee.code.oom.usernameautocomplete;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dee.code.oom.usernameautocomplete"
        , exclude = { RedissonAutoConfiguration.class, RedisAutoConfiguration.class})
public class UserNameMistakesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserNameMistakesApplication.class, args);
    }

}

