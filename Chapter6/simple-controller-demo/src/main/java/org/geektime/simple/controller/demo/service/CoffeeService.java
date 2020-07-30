package org.geektime.simple.controller.demo.service;

import org.geektime.simple.controller.demo.model.Coffee;
import org.geektime.simple.controller.demo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengfei.zhao
 * @data 2020/7/30 11:35
 */
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> getAllCoffee(){
        return coffeeRepository.findAll(Sort.by("id"));
    }

    public List<Coffee> findCoffeeByName(List<String> names){
        return coffeeRepository.findByNameOrderById(names);
    }
}
