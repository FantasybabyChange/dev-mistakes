package com.fantasybaby.dev.error.security.clientdata.trustclientip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fantasybaby.dev.error.security.clientdata.trustclientip")
public class TrustClientIpApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrustClientIpApplication.class, args);
    }
}

