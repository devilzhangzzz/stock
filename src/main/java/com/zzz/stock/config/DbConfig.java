package com.zzz.stock.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configurable
public class DbConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbConfig.class);


    @Value("${spring.datasource.url}")
    private String url;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        LOGGER.info("创建数据源：{}", url);
        org.apache.tomcat.jdbc.pool.DataSource dataSource = DataSourceBuilder.create().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
        dataSource.setMaxActive(2000);
        dataSource.setMinIdle(10);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource){
        return new JdbcTemplate();
    }
}
