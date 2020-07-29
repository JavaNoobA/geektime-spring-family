package org.geektime.mybatis.demo;

import lombok.extern.slf4j.Slf4j;
import org.geektime.mybatis.demo.mapper.CoffeeMapper;
import org.geektime.mybatis.demo.model.Coffee;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="org.geektime.mybatis.demo.mapper")
@Slf4j
public class MybatisDemoApplication implements CommandLineRunner {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MybatisDemoApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Coffee coffee = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 20))
                .build();
        coffeeMapper.save(coffee);
        log.info("Coffee = {}", coffeeMapper.queryById(1L));
    }
}
