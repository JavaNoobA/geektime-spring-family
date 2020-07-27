package org.geektime.datasourcedemo;

import com.sun.rowset.internal.Row;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 演示 SpringBoot 数据源
 *
 * @author pengfei.zhao
 * @date 2020/7/28 7:36
 */
@Slf4j
@SpringBootApplication
public class DataSourceDemoApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DataSourceDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConn();
        listData();
    }

    public void showConn() throws SQLException {
        log.info("【DataSource = 】" + dataSource);
        Connection conn = dataSource.getConnection();
        log.info("【Connection = 】" + conn.toString());
        conn.close();
    }

    public void listData(){
        jdbcTemplate.queryForList("SELECT * FROM FOO")
                .forEach(row -> log.info(row.toString()));
    }
}
