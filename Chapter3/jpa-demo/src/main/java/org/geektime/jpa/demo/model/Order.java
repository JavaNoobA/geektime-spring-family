package org.geektime.jpa.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 *
 * @author pengfei.zhao
 * @date 2020/7/28 21:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "T_ORDER")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String customer;

    @ManyToMany()
    @JoinTable(name = "T_COFFEE_ORDER")
    private List<Coffee> items;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
}
