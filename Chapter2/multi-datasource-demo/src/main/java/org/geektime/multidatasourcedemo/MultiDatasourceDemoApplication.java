package org.geektime.multidatasourcedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 演示多数据源配置
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        TransactionAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
@Slf4j
public class MultiDatasourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceDemoApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(value = "foo.datasource")
    public DataSourceProperties fooDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDataSource(){
        DataSourceProperties fooDataSourceProperties = fooDataSourceProperties();
        log.info(fooDataSourceProperties.getUrl());
        return fooDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource // @Resource 注入 Bean 是 byName 的方式，因为这里有多个 Bean (foo bar)
    public PlatformTransactionManager fooTranManager(DataSource fooDataSource){
        return new DataSourceTransactionManager(fooDataSource);
    }

    @Bean
    @ConfigurationProperties(value = "bar.datasource")
    public DataSourceProperties barDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource barDataSource(){
        DataSourceProperties barDataSourceProperties = barDataSourceProperties();
        log.info(barDataSourceProperties.getUrl());
        return barDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager barTranManager(DataSource barDataSource){
        return new DataSourceTransactionManager(barDataSource);
    }

}
