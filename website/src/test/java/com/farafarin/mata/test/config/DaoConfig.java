package com.farafarin.mata.test.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by K550 VX on 10/15/2017.
 */

@Configuration
@MapperScan({"com.farafarin.mata.common.dao","com.farafarin.mata.web.back.dao"})
public class DaoConfig {

    @Bean
    public DataSource dataSourceDev() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUsername("mata");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/mata-db");
        dataSource.setPassword("123456");

        return dataSource;
    }

//    @Bean
//    public JndiObjectFactoryBean dataSourceProd() {
//        JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
//        dataSource.setJndiName("java:comp/env/jdbc/josak");
//        return dataSource;
//    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSourceDev());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceDev());
//        sessionFactory.setTypeAliasesPackage("org.lanyonm.playground.domain");
        return sessionFactory;
    }

}
