package ru.clevertec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.clevertec.logging.LoggingHandlerBeanPostProcessor;

@Configuration
@PropertySource(value = "classpath:application.yaml", factory = ApplicationProperties.class)
@ComponentScan(basePackages = "ru.clevertec")
@EnableWebMvc
public class ApplicationConfig {

    @Bean
    public LoggingHandlerBeanPostProcessor loggingBeanPostProcessor() {
        return new LoggingHandlerBeanPostProcessor();
    }

}