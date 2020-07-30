package org.geektime.simple.controller.demo.repository;

import org.geektime.simple.controller.demo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author pengfei.zhao
 * @data 2020/7/30 11:32
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    List<Coffee> findByNameOrderById(List<String> names);
}
