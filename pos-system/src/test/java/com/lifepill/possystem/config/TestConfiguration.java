package com.lifepill.possystem.config;

import com.lifepill.possystem.util.mappers.EmployerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public EmployerMapper employerMapper() {
        return new EmployerMapper();
    }
}