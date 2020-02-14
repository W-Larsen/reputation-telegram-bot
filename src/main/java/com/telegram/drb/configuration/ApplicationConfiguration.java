package com.telegram.drb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

/**
 * Database configuration.
 *
 * @author Valentyn Korniienko
 */
@Configuration
@ComponentScan("com.telegram.drb")
@PropertySource(value = {"classpath:application.properties"})
@EnableScheduling
public class ApplicationConfiguration {

    @Autowired
    private Environment environment;

    /**
     * DataSource bean.
     *
     * @return dataSource
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        driverManagerDataSource.setUrl(environment.getProperty("jdbc.url"));
        driverManagerDataSource.setUsername(environment.getProperty("jdbc.username"));
        driverManagerDataSource.setPassword(environment.getProperty("jdbc.password"));
        return driverManagerDataSource;
    }

    /**
     * JdbcTemplate bean.
     *
     * @param dataSource the dataSource
     * @return JdbcTemplate
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    /**
     * RestTemplate bean.
     *
     * @return rest template
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}