package com.farafarin.mata.web.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

/**
 * Created by K550 VX on 10/15/2017.
 */

@Configuration
@MapperScan({"com.farafarin.mata.common.dao","com.farafarin.mata.web.back.dao"})
public class DaoConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("mata");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/mata-db");

        dataSource.setMaxActive(10);

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
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setTypeAliasesPackage("org.lanyonm.playground.domain");
        return sessionFactory;
    }

}
