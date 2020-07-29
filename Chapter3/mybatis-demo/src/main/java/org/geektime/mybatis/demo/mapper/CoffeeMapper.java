package org.geektime.mybatis.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.geektime.mybatis.demo.model.Coffee;

/**
 * @author pengfei.zhao
 * @data 2020/7/29 14:24
 */
@Mapper
public interface CoffeeMapper {

    @Insert("INSERT INTO T_COFFEE(name, price, create_time, update_time)"
            + "VALUES(#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Coffee coffee);

    @Select("SELECT * FROM T_COFFEE WHERE id=#{id}")
    @Results({
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
    })
    Coffee queryById(@Param("id") Long id);
}
