package org.ericghara.Lesson10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        // spring boot injects a datasource based on application.properties
        // (ie don't need another datasource bean like with vanilla spring)
        return new JdbcTemplate(dataSource);
    }
}
