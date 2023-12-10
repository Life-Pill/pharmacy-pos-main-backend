package com.lifepill.possystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails owner = User.withDefaultPasswordEncoder()
                .username("owner")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails branchManager =User.withDefaultPasswordEncoder()
                .username("branchManager")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails cashier =User.withDefaultPasswordEncoder()
                .username("cashier")
                .password("12345")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(owner,branchManager,cashier);
    }
}