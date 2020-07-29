package org.geektime.springbucks.repository;

import org.geektime.springbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pengfei.zhao
 * @data 2020/7/29 15:37
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
