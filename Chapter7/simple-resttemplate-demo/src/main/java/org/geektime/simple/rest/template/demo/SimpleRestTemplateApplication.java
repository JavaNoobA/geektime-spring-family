package org.geektime.simple.rest.template.demo;

import lombok.extern.slf4j.Slf4j;
import org.geektime.simple.rest.template.demo.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

/**
 * @author pengfei.zhao
 * @date 2020/8/1 10:25
 */
@SpringBootApplication
@Slf4j
public class SimpleRestTemplateApplication implements ApplicationRunner {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SimpleRestTemplateApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder)
    {
        return builder.build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/coffee/{id}")
                .build(1);

        ResponseEntity<Coffee> responseEntity = restTemplate.getForEntity(uri, Coffee.class);
        log.info("Response status: {}, Response header: {}",
                    responseEntity.getStatusCode(), responseEntity.getHeaders());
        log.info("Coffee: {}", responseEntity.getBody());

        String coffeeUri = "http://localhost:8080/coffee";
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(BigDecimal.valueOf(20.00))
                .build();
        Coffee coffee = restTemplate.postForObject(coffeeUri, latte, Coffee.class);
        log.info("New Coffee: {}", coffee);

        String result = restTemplate.getForObject(uri, String.class);
        log.info("String: {}", result);
    }
}
