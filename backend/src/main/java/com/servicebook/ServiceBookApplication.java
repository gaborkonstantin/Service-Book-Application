package com.servicebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.servicebook.model")
@EnableJpaRepositories("com.servicebook.repository")
public class ServiceBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBookApplication.class, args);
    }
}
