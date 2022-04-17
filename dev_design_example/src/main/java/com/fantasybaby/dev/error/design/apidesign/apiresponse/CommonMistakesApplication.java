package com.fantasybaby.dev.error.design.apidesign.apiresponse;

import com.fantasybaby.dev.mistake.common.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.design.apidesign.apiresponse")
public class CommonMistakesApplication {

    public static void main(String[] args) {
//        Utils.loadPropertySource(CommonMistakesApplication.class, "config.properties");
        SpringApplication.run(CommonMistakesApplication.class, args);
    }
}

