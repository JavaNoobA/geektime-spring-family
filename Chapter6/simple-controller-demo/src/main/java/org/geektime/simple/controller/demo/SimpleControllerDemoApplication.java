package org.geektime.simple.controller.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SimpleControllerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleControllerDemoApplication.class, args);
    }

}
