package com.fantasybaby.dee.code.spring.aopfeign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "org.geekbang.time.commonmistakes.springpart2.aopfeign.feign")
public class Config {
}
