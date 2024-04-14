package com.lifepill.possystem.config;

import com.lifepill.possystem.config.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.lifepill.possystem.entity.enums.Permission.*;
import static com.lifepill.possystem.entity.enums.Role.CASHIER;
import static com.lifepill.possystem.entity.enums.Role.OWNER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.antMatchers("/lifepill/v1/auth/*").permitAll()
                                .antMatchers("/lifepill/v1/test/**").permitAll()
                                //.antMatchers("/lifepill/v1/admin/**").hasAnyRole(OWNER_READ.name(), CASHIER.name())
                                .antMatchers("/lifepill/v1/admin/**").hasRole(OWNER.name())
                                //.antMatchers( "/lifepill/v1/admin/**").permitAll()
                                .antMatchers("/lifepill/v1/cashierNew/**").hasRole(CASHIER.name())
                               // .antMatchers(POST, "/lifepill/v1/cashierNew/**").hasAnyAuthority(CASHIER_CREATE.name(),OWNER_CREATE.name())
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
