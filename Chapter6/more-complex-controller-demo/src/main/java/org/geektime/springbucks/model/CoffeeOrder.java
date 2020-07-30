package org.geektime.springbucks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author pengfei.zhao
 * @date 2020/7/30 15:32
 */
@Entity
@Table(name = "T_ORDER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoffeeOrder extends BaseEntity{

    private String customer;

    @ManyToMany
    @JoinTable(name = "T_COFFEE_ORDER")
    private List<Coffee> items;

    @Enumerated
    private OrderState state;
}
