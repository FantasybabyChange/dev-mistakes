package com.fantasybaby.dev.error.other.troubleshootingtools.mat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.other.troubleshootingtools.mat",exclude = {DataSourceAutoConfiguration.class,JdbcRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class OOMApplication implements CommandLineRunner {

    @Autowired
    FooService fooService;

    //-Xmx512m -Xms512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=.
    public static void main(String[] args) {
        SpringApplication.run(OOMApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            fooService.oom();
        }
    }
}
