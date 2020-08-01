package org.geektime.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;

@Slf4j
public class GreetingApplicationRunner implements ApplicationRunner {

    public GreetingApplicationRunner(){
        log.info("Initializing GreetingApplicationRunner...");
    }

    public static void main(String[] args) {
        SpringApplication.run(GreetingApplicationRunner.class, args);
    }

    public void run(ApplicationArguments args) throws Exception {
        log.info("hello spring");
    }
}
