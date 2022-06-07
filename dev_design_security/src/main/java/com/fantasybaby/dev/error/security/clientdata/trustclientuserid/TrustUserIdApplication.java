package com.fantasybaby.dev.error.security.clientdata.trustclientuserid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.security.clientdata.trustclientuserid")
public class TrustUserIdApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TrustUserIdApplication.class, args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginRequiredArgumentResolver());
    }
}

