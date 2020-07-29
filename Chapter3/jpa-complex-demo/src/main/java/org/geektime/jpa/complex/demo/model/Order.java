package org.geektime.jpa.complex.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import java.util.List;

/**
 * @author pengfei.zhao
 * @data 2020/7/29 11:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Builder
@Entity(name = "T_ORDER")
public class Order extends BaseEntity{

    private String customer;

    @ManyToMany
    @JoinTable(name = "T_COFFEE_ORDER")
    @OrderBy("id")
    private List<Coffee> items;

    @Embedded
    private OrderState state;
}
