package com.fantasybaby.dee.code.serizlize.jsonignoreproperties;

import com.fantasybaby.dee.common.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dee.code.serizlize.jsonignoreproperties"
        , exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, RedissonAutoConfiguration.class, RedisAutoConfiguration.class})
public class CommonMistakesApplication {

    public static void main(String[] args) {
        Utils.loadPropertySource(CommonMistakesApplication.class, "jackson.properties");
        SpringApplication.run(CommonMistakesApplication.class, args);
    }

//    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
        return objectMapper;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }
}

