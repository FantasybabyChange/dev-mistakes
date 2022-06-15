package com.fantasybaby.dev.error.security.sensitivedata.storeidcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.security.sensitivedata.storeidcard"
,exclude = {RedisAutoConfiguration.class})
public class StoreIdCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreIdCardApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

