package org.geektime.jpa.demo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 *
 * @author pengfei.zhao
 * @date 2020/7/28 21:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "T_MENU")
public class Coffee implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name="currencyCode", value = "CNY")})
    private Money price;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
}
