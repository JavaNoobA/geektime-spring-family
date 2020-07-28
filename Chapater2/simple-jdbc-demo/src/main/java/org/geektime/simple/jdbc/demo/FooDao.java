package org.geektime.simple.jdbc.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author pengfei.zhao
 * @data 2020/7/28 14:01
 */
@Repository
@Slf4j
public class FooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insertData(){
        Arrays.asList("a", "b").forEach(bar -> {
            jdbcTemplate.update("INSERT INTO FOO (BAR) VALUES(?)", bar);
        });

        HashMap<String, String> row = new HashMap<>();
        row.put("BAR", "c");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("ID of c = {}", id);
    }

    public void listData(){
        // 单个查找
        log.info("data count = {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO", Long.class));
        // 查询多条
        List<String> bars = jdbcTemplate.queryForList("SELECT BAR FROM FOO", String.class);
        bars.forEach(b -> log.info("bar = {}", b));
        // 查询返回对象
        List<Foo> foos = jdbcTemplate.query("SELECT * FROM FOO", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Foo.builder()
                        .id(rs.getLong(1))
                        .bar(rs.getString(2))
                        .build();
            }
        });
        foos.forEach(f -> log.info("FOO = {}", f));
    }
}
