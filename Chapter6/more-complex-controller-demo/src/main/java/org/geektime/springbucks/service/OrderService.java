package org.geektime.springbucks.service;

import lombok.extern.slf4j.Slf4j;
import org.geektime.springbucks.model.Coffee;
import org.geektime.springbucks.model.CoffeeOrder;
import org.geektime.springbucks.model.OrderState;
import org.geektime.springbucks.repository.CoffeeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author pengfei.zhao
 * @date 2020/7/30 15:48
 */
@Service
@Slf4j
public class OrderService {
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder getOrder(long id){
        return orderRepository.getOne(id);
    }

    public CoffeeOrder createOrder(String customer, Coffee...coffees){
        CoffeeOrder saved = CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffees))
                .build();
        orderRepository.save(saved);
        log.info("New Order Found: {}", saved);
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state){
        if (order.getState().compareTo(state) < 0){
            log.warn("Order state: {} can't less than {}", order.getState(), state);
            return false;
        }
        order.setState(state);
        orderRepository.save(order);
        return true;
    }
}
