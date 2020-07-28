package org.geektime.jpa.demo.repository;

import org.geektime.jpa.demo.model.Coffee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pengfei.zhao
 * @date 2020/7/29 7:20
 */
@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
