package org.geektime.springbucks.repository;

import org.geektime.springbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author pengfei.zhao
 * @date 2020/7/30 15:35
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    List<Coffee> findByNameInOrderById(List<String> list);
    Coffee findByName(String name);
}
