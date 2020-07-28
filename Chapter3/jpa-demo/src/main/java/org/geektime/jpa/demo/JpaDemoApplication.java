package org.geektime.jpa.demo;

import lombok.extern.slf4j.Slf4j;
import org.geektime.jpa.demo.model.Coffee;
import org.geektime.jpa.demo.model.Order;
import org.geektime.jpa.demo.repository.CoffeeRepository;
import org.geektime.jpa.demo.repository.OrderRepository;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@SpringBootApplication
@Slf4j
public class JpaDemoApplication implements CommandLineRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    public void initOrders(){
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(latte);
        log.info("Coffee: {}", latte);

        Coffee cappuccino = Coffee.builder()
                .name("cappuccino")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0))
                .build();
        coffeeRepository.save(cappuccino);
        log.info("Coffee: {}", cappuccino);

        Order order = Order.builder()
                .customer("miki")
                .items(Collections.singletonList(latte))
                .build();
        log.info("Order: {}", order);

        order = Order.builder()
                .customer("miki")
                .items(Arrays.asList(latte, cappuccino))
                .build();
        log.info("Order: {}", order);
    }

    @Override
    public void run(String... args) throws Exception {
        initOrders();
    }
}
