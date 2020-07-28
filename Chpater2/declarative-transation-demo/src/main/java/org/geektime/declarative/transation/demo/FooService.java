package org.geektime.declarative.transation.demo;

/**
 * @author pengfei.zhao
 * @data 2020/7/28 14:56
 */
public interface FooService {
    /**
     * 插入一条记录
     */
    void insertRecord();

    /**
     * 插入数据的时候抛出异常
     */
    void insertThenRollback();

    /***
     * 调用 Insert 的时候抛出异常
     */
    void invokeInsertThenRollback();
}
