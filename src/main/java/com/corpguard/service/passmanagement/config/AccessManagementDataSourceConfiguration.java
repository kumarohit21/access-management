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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "accessEntityManagerFactory",
        transactionManagerRef = "accessTransactionManager",
        basePackages = {"com.corpguard.service.passmanagement.repo.accesscard"})
public class AccessManagementDataSourceConfiguration {

    @Bean(name = "accessDataSourceProperties")
    @ConfigurationProperties("spring.datasource-accessmgmt")
    public DataSourceProperties accessDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "accessDataSource")
    @ConfigurationProperties("spring.datasource-accessmgmt")
    public DataSource accessDataSource(@Qualifier("accessDataSourceProperties") DataSourceProperties accessDataSourceProperties) {
        return accessDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "accessEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean accessEntityManagerFactory(
           @Qualifier("accessEntityManagerFactoryBuilder") EntityManagerFactoryBuilder accessEntityManagerFactoryBuilder, @Qualifier("accessDataSource") DataSource accessDataSource) {

        Map<String, String> accessJpaProperties = new HashMap<>();
        accessJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        accessJpaProperties.put("hibernate.hbm2ddl.auto", "update");

        return accessEntityManagerFactoryBuilder
                .dataSource(accessDataSource)
                .packages("com.corpguard.service.passmanagement.entity.accesscard")
                .persistenceUnit("accessDataSource")
                .properties(accessJpaProperties)
                .build();
    }

    @Primary
    @Bean(name = "accessTransactionManager")
    public PlatformTransactionManager accessTransactionManager(
            @Qualifier("accessEntityManagerFactory") EntityManagerFactory accessEntityManagerFactory) {

        return new JpaTransactionManager(accessEntityManagerFactory);
    }

    @Bean(name="accessEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}