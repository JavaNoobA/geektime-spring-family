package org.geektime.simple.jdbc.demo;

import lombok.Builder;
import lombok.Data;

/**
 * @author pengfei.zhao
 * @data 2020/7/28 13:55
 */
@Data
@Builder
public class Foo {

    private Long id;

    private String bar;
}
