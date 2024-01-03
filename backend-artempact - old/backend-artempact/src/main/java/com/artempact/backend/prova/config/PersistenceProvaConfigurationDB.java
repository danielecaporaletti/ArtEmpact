package com.artempact.backend.prova.config;

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
        basePackages = "com.artempact.backend.prova",
        entityManagerFactoryRef = "provaEntityManager",
        transactionManagerRef = "provaTransactionManager"
)
public class PersistenceProvaConfigurationDB {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean provaEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(provaDataSource());
        em.setPackagesToScan(
                new String[] { "com.artempact.backend.prova.entity" });

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("prova.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("prova.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource provaDataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("prova.jdbc.url"));
        dataSource.setUsername(env.getProperty("prova.jdbc.user"));
        dataSource.setPassword(env.getProperty("prova.jdbc.pass"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager provaTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                provaEntityManager().getObject());
        return transactionManager;
    }
}

