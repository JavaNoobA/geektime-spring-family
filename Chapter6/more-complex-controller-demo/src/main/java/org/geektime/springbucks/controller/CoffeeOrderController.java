package org.geektime.springbucks.controller;

import lombok.extern.slf4j.Slf4j;
import org.geektime.springbucks.controller.request.NewOrderRequest;
import org.geektime.springbucks.model.Coffee;
import org.geektime.springbucks.model.CoffeeOrder;
import org.geektime.springbucks.service.CoffeeService;
import org.geektime.springbucks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pengfei.zhao
 * @date 2020/7/30 16:25
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping(path = "/{id}")
    public CoffeeOrder getById(@PathVariable("id") long id){
        return orderService.getOrder(id);
    }

    @PostMapping(path = "/")
    public CoffeeOrder createOrder(@RequestBody NewOrderRequest orderRequest){
        List<Coffee> coffeeList = coffeeService.getCoffeeByNames(orderRequest.getItems());
        Coffee[] coffees = coffeeList.toArray(new Coffee[]{});
        CoffeeOrder saved = orderService.createOrder(orderRequest.getCustomer(), coffees);
        log.info("New Order Found: {}", saved);
        return saved;
    }
}
