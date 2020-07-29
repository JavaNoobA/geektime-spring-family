package org.geektime.mybatis.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

/**
 * @author pengfei.zhao
 * @data 2020/7/29 14:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coffee {

    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
