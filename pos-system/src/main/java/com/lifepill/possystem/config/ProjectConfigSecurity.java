package com.lifepill.possystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ProjectConfigSecurity {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
                        .antMatchers(
                                "/lifepill/v1/employers/*",
                                "/lifepill/v1/branch/*",
                                "/lifepill/v1/item-Category/*",
                                "/lifepill/v1/item/*",
                                "/lifepill/v1/order/*",
                                "/lifepill/v1/supplierCompanies/*",
                                "/lifepill/v1/supplier/*",
                                "/swagger-ui.html#/"
                        ).authenticated()
                        .antMatchers(
                                "/lifepill/v1/notices/*",
                                "/lifepill/v1/contact/*"
                        ).permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }
}
