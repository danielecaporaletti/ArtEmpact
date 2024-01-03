package com.artempact.backend.mysqlGeographic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(
        basePackages = "com.artempact.backend.mysqlGeographic",
        entityManagerFactoryRef = "italiaEntityManager",
        transactionManagerRef = "italiaTransactionManager"
)
public class PersistenceGeographicConfigurationDB {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean italiaEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(italiaDataSource());
        em.setPackagesToScan(
                new String[] { "com.artempact.backend.mysqlGeographic.entity" });

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("geographic.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("geographic.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource italiaDataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("italia.jdbc.url"));
        dataSource.setUsername(env.getProperty("geographic.jdbc.user"));
        dataSource.setPassword(env.getProperty("geographic.jdbc.pass"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager italiaTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                italiaEntityManager().getObject());
        return transactionManager;
    }
}

