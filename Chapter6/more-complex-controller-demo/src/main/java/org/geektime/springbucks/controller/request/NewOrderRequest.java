package org.geektime.springbucks.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author pengfei.zhao
 * @date 2020/7/30 16:27
 */
@Setter
@Getter
@ToString
public class NewOrderRequest {

    private String customer;

    private List<String> items;
}
