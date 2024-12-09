package ru.clevertec.config;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:application.yaml", factory = ApplicationProperties.class)
@ComponentScan(basePackages = "ru.clevertec")
@EnableJpaRepositories("ru.clevertec.repository")
@EnableTransactionManagement
public class JpaConfig {

    @Value("${datasource.driver_class}")
    private String driver;
    @Value("${datasource.url_db}")
    private String url;
    @Value("${datasource.username_db}")
    private String username;
    @Value("${datasource.password_db}")
    private String password;

    @Value("${hibernate.show_sql}")
    private String showSql;
    @Value("${hibernate.ddl_auto}")
    private String ddlAuto;
    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${liquibase.change_log}")
    private String changeLog;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();

        springLiquibase.setDataSource(dataSource());
        springLiquibase.setChangeLog(changeLog);

        return springLiquibase;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan("ru.clevertec");

        Properties properties = new Properties();
        properties.putAll(
                Map.of(
                        "hibernate.show-sql", showSql,
                        "hibernate.ddl-auto", ddlAuto,
                        "hibernate.dialect", dialect
                )
        );
        em.setJpaProperties(properties);

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());

        return transactionManager;
    }

}