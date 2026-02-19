package com.java.hallaemallae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HallaeMallaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HallaeMallaeApplication.class, args);
    }

}
