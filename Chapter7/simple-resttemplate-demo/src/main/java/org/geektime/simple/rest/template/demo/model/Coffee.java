package org.geektime.simple.rest.template.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author pengfei.zhao
 * @date 2020/8/1 10:31
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    private Long id;
    private String name;
    private BigDecimal price; // 先用BigDecimal，下次换Money
    private Date createTime;
    private Date updateTime;
}
