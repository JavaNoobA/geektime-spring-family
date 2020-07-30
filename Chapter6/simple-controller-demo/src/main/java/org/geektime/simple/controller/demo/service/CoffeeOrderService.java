package org.geektime.simple.controller.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.geektime.simple.controller.demo.model.Coffee;
import org.geektime.simple.controller.demo.model.CoffeeOrder;
import org.geektime.simple.controller.demo.model.OrderState;
import org.geektime.simple.controller.demo.repository.CoffeeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author pengfei.zhao
 * @data 2020/7/30 11:35
 */
@Service
@Slf4j
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder createOrder(String customer, Coffee... coffees){
        CoffeeOrder saved = orderRepository.save(
                CoffeeOrder.builder().customer(customer).items(Arrays.asList(coffees)).build());
        log.info("New Order Found: {}", saved);
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state){
        if (order.getState().compareTo(state) < 0){
            log.warn("Order State: {} can't less than given State: {}", order.getState(), state);
            return false;
        }
        order.setState(state);
        orderRepository.save(order);
        return true;
    }
}
