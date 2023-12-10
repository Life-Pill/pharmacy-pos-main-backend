package com.lifepill.possystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfigSecurity {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/lifepill/v1/cashier/","/lifepill/v1/item").authenticated()
                .antMatchers("/lifepill/v1/cashier/get-all-cashiers").authenticated()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }
}