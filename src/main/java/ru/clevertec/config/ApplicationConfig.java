package ru.clevertec.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource(value = "classpath:application.yaml", factory = ApplicationProperties.class)
@ComponentScan(basePackages = "ru.clevertec")
@EnableWebMvc
public class ApplicationConfig {

}