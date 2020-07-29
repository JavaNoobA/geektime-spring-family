package org.geektime.jpa.complex.demo;

import lombok.extern.slf4j.Slf4j;
import org.geektime.jpa.complex.demo.model.Coffee;
import org.geektime.jpa.complex.demo.model.Order;
import org.geektime.jpa.complex.demo.repository.CoffeeRepository;
import org.geektime.jpa.complex.demo.repository.OrderRepository;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class JpaComplexDemoApplication implements CommandLineRunner {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaComplexDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initOrders();
        listOrders();
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

    public void listOrders(){
        orderRepository
                .findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(c -> log.info("Loading {}", c));

        List<Order> top3Orders = orderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("findTop3ByOrderByUpdateTimeDescIdAsc: {}", getJoinedId(top3Orders));

        List<Order> latteOrders = orderRepository.findOrdersByCustomer("miki");
        log.info("findOrdersByCustomer: {}", getJoinedId(latteOrders));

        List<Order> coffeeOrders = orderRepository.findByItems_Name("latte");
        log.info("findByItems_Name: {}", getJoinedId(coffeeOrders));
    }

    public String getJoinedId(List<Order> orders){
        return orders.stream().map(o -> o.getId().toString()).collect(Collectors.joining(","));
    }
}
