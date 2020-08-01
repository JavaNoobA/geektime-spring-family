package org.geektime.springbucks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class MoreComplexControllerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoreComplexControllerDemoApplication.class, args);
    }

}
