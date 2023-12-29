package com.backend.api.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = {"com.backend.api.repository.sqlite"},
        sqlSessionFactoryRef = "sqliteSqlSessionFactory")
public class SqliteDataSourceConfig {

    @Bean(name = {"sqliteDataSource"})
    public DataSource dataSource(
            @Qualifier("sqliteHikariConfig") HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = {"sqliteHikariConfig"})
    @ConfigurationProperties(prefix = "spring.datasource.sqlite.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = {"sqliteTxManager"})
    public PlatformTransactionManager txManager(
            @Qualifier("sqliteDataSource")DataSource dataSource) {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        transactionManager.setRollbackOnCommitFailure(true);
        return transactionManager;
    }

    @Bean(name = {"sqliteSqlSessionFactory"})
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("sqliteDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
        return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
    }
}

