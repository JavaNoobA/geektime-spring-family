package org.geektime.springbucks.repository;

import org.geektime.springbucks.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pengfei.zhao
 * @date 2020/7/30 15:35
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
