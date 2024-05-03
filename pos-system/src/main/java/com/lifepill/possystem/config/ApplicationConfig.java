package com.lifepill.possystem.config;

import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import org.springframework.context.annotation.Configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration class for application settings.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final EmployerRepository employerRepository;

    /**
     * Provides an instance of BCryptPasswordEncoder for password encoding.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Retrieves a user details service based on the employer's email.
     *
     * @return UserDetailsService implementation
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> employerRepository.findByEmployerEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employer not found"));
    }

    /**
     * Provides an authentication provider using DaoAuthenticationProvider.
     *
     * @return AuthenticationProvider implementation
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Retrieves an authentication manager based on the authentication configuration.
     *
     * @param config AuthenticationConfiguration instance
     * @return AuthenticationManager instance
     * @throws Exception if there is an issue retrieving the authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Defines a CorsFilter bean to enable Cross-Origin Resource Sharing.
     *
     * @return CorsFilter instance
     */
    /**
     * Defines a CorsFilter bean to enable Cross-Origin Resource Sharing.
     *
     * @return CorsFilter instance
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // Configure CORS settings as needed
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}