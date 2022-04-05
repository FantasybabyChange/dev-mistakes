package com.fantasybaby.dee.code.spring.propertysource;

import lombok.extern.slf4j.Slf4j;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.StandardEnvironment;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dee.code.spring.propertysource"
        , exclude = {RedissonAutoConfiguration.class, RedisAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Slf4j
public class StandardPropertySourceApplication {
    @Autowired
    private StandardEnvironment env;

    public static void main(String[] args) {
        //环境变量添加 MANAGEMENT_SERVER_PORT=12345 MANAGEMENT_SERVER_IP=192.168.0.2
        new SpringApplicationBuilder(StandardPropertySourceApplication.class)
                // .REACTIVE, .SERVLET
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @PostConstruct
    public void init() {
        env.getProperty("user.name");
        Arrays.asList("user.name", "management.server.port").forEach(key -> {
            env.getPropertySources().forEach(propertySource -> {
                if (propertySource.containsProperty(key)) {
                    log.info("{} -> {} 实际取值：{}", propertySource, propertySource.getProperty(key), env.getProperty(key));
                }
            });
        });

        System.out.println("配置优先级：");
        env.getPropertySources().stream().forEach(System.out::println);
    }
}

