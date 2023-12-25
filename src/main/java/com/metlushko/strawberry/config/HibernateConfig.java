package com.metlushko.strawberry.config;


import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.metlushko.strawberry")
@PropertySource("classpath:hibernate.properties")
public class HibernateConfig {
    private final Environment env;

    public HibernateConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setPackagesToScan("com.metlushko.strawberry.entity");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        localSessionFactoryBean.setDataSource(dataSource());

        return localSessionFactoryBean;

    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));

        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty("hibernate.driver_class"));
        dataSource.setUrl(env.getRequiredProperty("hibernate.connection.url"));
        dataSource.setUsername(env.getRequiredProperty("hibernate.connection.username"));
        dataSource.setPassword(env.getRequiredProperty("hibernate.connection.password"));

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(){

        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
        return hibernateTransactionManager;

    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(env.getRequiredProperty("liquibase.changeLogFiles"));
        liquibase.setDataSource(dataSource());
        return liquibase;
    }


}
