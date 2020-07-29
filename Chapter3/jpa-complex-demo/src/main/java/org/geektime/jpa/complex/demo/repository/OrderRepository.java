package org.geektime.jpa.complex.demo.repository;

import org.geektime.jpa.complex.demo.model.Order;

import java.util.List;

/**
 * @author pengfei.zhao
 * @data 2020/7/29 11:49
 */
public interface OrderRepository extends BaseRepository<Order, Long>{

    List<Order> findOrdersByCustomer(String customer);

    List<Order> findByItems_Name(String name);
}
