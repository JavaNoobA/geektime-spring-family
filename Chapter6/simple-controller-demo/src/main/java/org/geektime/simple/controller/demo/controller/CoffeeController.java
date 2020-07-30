package org.geektime.simple.controller.demo.controller;

import org.geektime.simple.controller.demo.model.Coffee;
import org.geektime.simple.controller.demo.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pengfei.zhao
 * @data 2020/7/30 11:35
 */
@RequestMapping("/coffee")
@RestController
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping
    public List<Coffee> getAllCoffee(){
        return coffeeService.getAllCoffee();
    }
}
