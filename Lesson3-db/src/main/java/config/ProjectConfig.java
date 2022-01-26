package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@ComponentScan(basePackages = "repositories")
public class ProjectConfig {

    @Bean
    public DataSource dataSource() {
        var dataSource =  new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/demo");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public JdbcTemplate JdbcTemplateTemplate() {
        return new JdbcTemplate(dataSource() );
    }
}
