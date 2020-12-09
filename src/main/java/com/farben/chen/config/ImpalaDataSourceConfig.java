package com.farben.chen.config;




import javax.sql.DataSource;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class ImpalaDataSourceConfig {


    @Primary
    @Bean(name = "impalaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.impala")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "impalaJdbcTemplate")
    public JdbcTemplate jdbcTemplateImpala(@Qualifier("impalaDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
















}
