package com.lifepill.possystem.config;

import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.filter.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/contact")
                               // new AntPathRequestMatcher("/save-with-image")
                        )
                )
                .csrf().disable()
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/lifepill/v1/branch/**").hasRole(Role.Owner.name())
                        .antMatchers("/lifepill/v1/contact/**").hasAnyRole(Role.Cashier.name(), Role.BranchManager.name(),Role.Owner.name())
                        .antMatchers("/lifepill/v1/employers/**").hasRole(Role.BranchManager.name())
                        .antMatchers("/lifepill/v1/item-Category/**").hasAnyRole(Role.Cashier.name(), Role.BranchManager.name(),Role.Owner.name())
                        .antMatchers("/lifepill/v1/item/**").hasAnyRole(Role.Cashier.name(), Role.BranchManager.name(),Role.Owner.name())
                        .antMatchers("/lifepill/v1/notices/**").hasRole(Role.Owner.name())
                        .antMatchers("/lifepill/v1/order/**").hasAnyRole(Role.Cashier.name(), Role.BranchManager.name(),Role.Owner.name())
                        .antMatchers("/lifepill/v1/supplierCompanies/**").hasAnyRole(Role.Cashier.name(), Role.BranchManager.name(),Role.Owner.name())
                        .antMatchers("/lifepill/v1/supplier/**").hasAnyRole(Role.Cashier.name(), Role.BranchManager.name(),Role.Owner.name())
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
