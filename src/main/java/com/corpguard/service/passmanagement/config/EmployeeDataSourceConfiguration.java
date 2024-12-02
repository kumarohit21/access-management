package com.corpguard.service.passmanagement.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "employeeEntityManagerFactory",
        transactionManagerRef = "employeeTransactionManager",
        basePackages = {"com.corpguard.service.passmanagement.repo.employee"})
public class EmployeeDataSourceConfiguration {

    @Bean(name = "employeeDataSourceProperties")
    @ConfigurationProperties("spring.datasource-employee")
    public DataSourceProperties employeeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "employeeDataSource")
    @ConfigurationProperties("spring.datasource-employee.configuration")
    public DataSource employeeDataSource(@Qualifier("employeeDataSourceProperties") DataSourceProperties employeeDataSourceProperties) {
        return employeeDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "employeeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(
            EntityManagerFactoryBuilder employeeEntityManagerFactoryBuilder, @Qualifier("employeeDataSource") DataSource employeeDataSource) {

        Map<String, String> employeeJpaProperties = new HashMap<>();
        employeeJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        employeeJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return employeeEntityManagerFactoryBuilder
                .dataSource(employeeDataSource)
                .packages("com.corpguard.service.passmanagement.entity.employee")
                .persistenceUnit("employeeDataSource")
                .properties(employeeJpaProperties)
                .build();
    }

    @Primary
    @Bean(name = "employeeTransactionManager")
    public PlatformTransactionManager employeeTransactionManager(
            @Qualifier("employeeEntityManagerFactory") EntityManagerFactory employeeEntityManagerFactory) {

        return new JpaTransactionManager(employeeEntityManagerFactory);
    }
}