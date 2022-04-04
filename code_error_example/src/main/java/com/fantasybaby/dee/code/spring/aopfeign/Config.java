package com.fantasybaby.dee.code.spring.aopfeign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.fantasybaby.dee.code.spring.aopfeign")
public class Config {
}
