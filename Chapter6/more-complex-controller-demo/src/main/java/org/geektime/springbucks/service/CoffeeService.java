package org.geektime.springbucks.service;

import org.geektime.springbucks.model.Coffee;
import org.geektime.springbucks.repository.CoffeeRepository;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengfei.zhao
 * @date 2020/7/30 15:41
 */
@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public Coffee addCoffee(String name, Money price){
        return coffeeRepository.save(Coffee.builder().name(name).price(price).build());
    }

    public List<Coffee> getAllCoffee(){
        return coffeeRepository.findAll();
    }

    public Coffee getCoffee(String name){
        return coffeeRepository.findByName(name);
    }

    public Coffee getCoffee(Long id){
        return coffeeRepository.getOne(id);
    }

    public List<Coffee> getCoffeeByNames(List<String> names){
        return coffeeRepository.findByNameInOrderById(names);
    }
}
