package org.geektime.declarative.transation.demo;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pengfei.zhao
 * @data 2020/7/28 14:58
 */
@Service
public class FooServiceImpl implements FooService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void insertRecord() {
        jdbcTemplate.update("INSERT INTO FOO (BAR) VALUES (?)", "AAA");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException{
        jdbcTemplate.update("INSERT INTO FOO (BAR) VALUES (?)", "BBB");
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() {
        // 如果想让事务生效
        /**1.
         * @Autowired
         * private FooService fooService;
         * fooService.invokeThenRollback();
         *
         * 2. (FooService)AopContext.currentProxy().invokeThenRollback()
         */
        insertThenRollback();
    }
}
