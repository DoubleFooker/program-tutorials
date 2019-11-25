package com.ognice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableWebSecurity
public class SecurityDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApp.class, args);
    }
}
