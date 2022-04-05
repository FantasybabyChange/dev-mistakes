package com.fantasybaby.dee.code.spring.aopfeign;

import com.fantasybaby.dee.common.Utils;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dee.code.spring.aopfeign"
        , exclude = { RedissonAutoConfiguration.class, RedisAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class FeignAopMistakesApplication {

    public static void main(String[] args) {
        Utils.loadPropertySource(FeignAopMistakesApplication.class, "feign.properties");
//        SpringApplication.run(FeignAopMistakesApplication.class, args);
        new SpringApplicationBuilder(FeignAopMistakesApplication.class)
                // .REACTIVE, .SERVLET
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}

