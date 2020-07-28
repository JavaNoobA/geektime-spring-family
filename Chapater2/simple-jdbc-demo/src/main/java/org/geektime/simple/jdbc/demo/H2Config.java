package org.geektime.simple.jdbc.demo;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * H2 database config
 *
 * @author pengfei.zhao
 * @date 2020/7/28 19:51
 */
@Configuration
public class H2Config {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers", "-webDaemon", "-webPort", "8082");
    }
}
