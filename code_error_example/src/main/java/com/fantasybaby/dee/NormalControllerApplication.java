package com.fantasybaby.dee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author fantasybaby
 * //
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@EnableEurekaClient
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.fantasybaby.dee"})
public class NormalControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NormalControllerApplication.class, args);
    }

}
