package com.fantasybaby.dev.error.design.productionready.health;

import com.fantasybaby.dev.mistake.common.Utils;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.design.productionready.health"
        , exclude = {RedissonAutoConfiguration.class, RedisAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, RabbitAutoConfiguration.class})
public class HealthIndicatorApplication {

    public static void main(String[] args) {
        Utils.loadPropertySource(HealthIndicatorApplication.class, "actuator.properties");

        SpringApplication.run(HealthIndicatorApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

