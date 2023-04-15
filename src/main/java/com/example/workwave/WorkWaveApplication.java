package com.example.workwave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class WorkWaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkWaveApplication.class, args);
    }

}
