package org.geektime.druiddemo;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * Conn Log Filter
 *
 * @author pengfei.zhao
 * @date 2020/7/28 8:39
 */
@Slf4j
public class ConnectionLogFilter extends FilterEventAdapter {

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        log.info("CONNECTION BEFORE");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        log.info("CONNECTION AFTER");
    }
}
