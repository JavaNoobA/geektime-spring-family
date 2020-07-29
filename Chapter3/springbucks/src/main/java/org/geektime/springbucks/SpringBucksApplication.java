package org.geektime.springbucks;

import lombok.extern.slf4j.Slf4j;
import org.geektime.springbucks.model.Coffee;
import org.geektime.springbucks.model.CoffeeOrder;
import org.geektime.springbucks.model.OrderState;
import org.geektime.springbucks.repository.CoffeeRepository;
import org.geektime.springbucks.service.CoffeeOrderService;
import org.geektime.springbucks.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class SpringBucksApplication implements CommandLineRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("All Coffee: {}", coffeeRepository.findAll());

        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()){
            CoffeeOrder order = orderService.createOrder("miki", latte.get());
            log.info("Update INIT to PAID: {}", orderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", orderService.updateState(order, OrderState.INIT));
        }
    }
}
