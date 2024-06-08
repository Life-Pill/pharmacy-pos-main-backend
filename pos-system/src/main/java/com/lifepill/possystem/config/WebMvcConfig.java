package com.lifepill.possystem.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


/*
    @Bean
    public EmployerAuthDetailsResponseDTOToStringConverter employerAuthDetailsResponseDTOToStringConverter(ObjectMapper objectMapper) {
        return new EmployerAuthDetailsResponseDTOToStringConverter(objectMapper);
    }
*/

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "http://localhost:3000")
                .allowedMethods("*")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true);
    }

 /*   @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(employerAuthDetailsResponseDTOToStringConverter(null));
        // Pass null for ObjectMapper argument to let Spring inject it automatically
    }*/
}
