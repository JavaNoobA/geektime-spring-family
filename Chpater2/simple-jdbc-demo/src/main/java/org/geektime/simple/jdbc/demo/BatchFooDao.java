package org.geektime.simple.jdbc.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量插入
 * @author pengfei.zhao
 * @data 2020/7/28 14:20
 */
@Repository
public class BatchFooDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void batchInsert(){
        jdbcTemplate.batchUpdate("INSERT INTO FOO (BAR) VALUES (?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, "b-" + i); // parameterIndex=1 就表示游标移到了第一位 也就是 BAR 上
            }

            @Override
            public int getBatchSize() {
                return 10;
            }
        });

        List<Foo> fooList = new ArrayList<>();
        fooList.add(Foo.builder().id(100L).bar("b-100").build());
        fooList.add(Foo.builder().id(101L).bar("b-101").build());
        namedParameterJdbcTemplate.batchUpdate("INSERT INTO FOO(ID, BAR) VALUES (:id, :bar)", SqlParameterSourceUtils.createBatch(fooList));
    }
}
