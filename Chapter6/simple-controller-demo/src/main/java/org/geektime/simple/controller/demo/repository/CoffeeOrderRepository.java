package org.geektime.simple.controller.demo.repository;

import org.geektime.simple.controller.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pengfei.zhao
 * @data 2020/7/30 11:33
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
