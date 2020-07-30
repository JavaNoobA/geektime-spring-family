package org.geektime.simple.controller.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author pengfei.zhao
 * @data 2020/7/30 14:06
 */

@Getter
@Setter
@ToString
public class NewRequest {
    private String customer;

    private List<String> items;
}
