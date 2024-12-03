package ru.clevertec.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import ru.clevertec.util.HibernateUtil;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public SessionFactory sessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

}
