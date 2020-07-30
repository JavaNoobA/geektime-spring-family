package org.geektime.simple.controller.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.geektime.simple.controller.demo.controller.request.NewRequest;
import org.geektime.simple.controller.demo.model.Coffee;
import org.geektime.simple.controller.demo.model.CoffeeOrder;
import org.geektime.simple.controller.demo.service.CoffeeOrderService;
import org.geektime.simple.controller.demo.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengfei.zhao
 * @data 2020/7/30 11:35
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {

    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder createOrder(@RequestBody NewRequest newRequest){
        log.info("Receive new order");
        Coffee[] coffees = coffeeService.findCoffeeByName(newRequest.getItems()).toArray(new Coffee[]{});
        return orderService.createOrder(newRequest.getCustomer(), coffees);
    }
}
