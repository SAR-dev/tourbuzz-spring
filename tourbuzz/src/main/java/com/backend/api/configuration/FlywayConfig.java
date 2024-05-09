package com.backend.api.configuration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {
    @Bean(name = "postgresFlyway", initMethod = "migrate")
    public Flyway createFirstFlyway(@Qualifier("postgresDataSource") DataSource dataSource) {
        return new Flyway(
                new FluentConfiguration().locations("db/migration/postgres").dataSource(dataSource));
    }

    @Bean(name = "sqliteFlyway", initMethod = "migrate")
    public Flyway createSecondFlyway(@Qualifier("sqliteDataSource") DataSource dataSource) {
        return new Flyway(
                new FluentConfiguration().locations("db/migration/sqlite").dataSource(dataSource));
    }
}
