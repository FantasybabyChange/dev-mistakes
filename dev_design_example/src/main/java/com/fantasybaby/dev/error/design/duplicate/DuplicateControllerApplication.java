package com.fantasybaby.dev.error.design.duplicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author fantasybaby
 * //
 */

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.design.duplicate")
public class DuplicateControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuplicateControllerApplication.class, args);
    }

}
