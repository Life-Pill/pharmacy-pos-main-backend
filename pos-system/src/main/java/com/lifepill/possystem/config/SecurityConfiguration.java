package com.lifepill.possystem.config;

import com.lifepill.possystem.filter.JwtAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.lifepill.possystem.entity.enums.Role.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Configuration class for Security.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    AuthenticationProvider authenticationProvider;
    JwtAuthFilter jwtAuthFilter;

    /**
     * Configures security filter chain.
     *
     * @param http HttpSecurity object
     * @return SecurityFilterChain object
     * @throws Exception If an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
                .authorizeHttpRequests(req ->
                        req.antMatchers("/lifepill/v1/auth/**",
                                        "/lifepill/v1/session/**",
                                        "/swagger-ui/index.html#/",
                                        "/swagger-ui.html#/",
                                        "/actuator/**",
                                        "/test2/**",
                                        "/lifepill/v1/test/**",
                                        "lifepill/v1/contact/**",
                                        "/lifepill/v1/notices/**",
                                        "/swagger-ui/index.html#/v3/api-docs",
                                        "/swagger-ui.html#/",
                                        "/swagger-ui/**",
                                        "/v2/api-docs",
                                        "/webjars/**",
                                        "/v3/api-docs",
                                        "/lifepill/v1/medicine-finding/find-medicine",
                                "/lifepill/v1/supplierCompanies/getAll-Supplier-Companies",
                                "/lifepill/v1/order/getAllOrdersWithDetails"
                                ).permitAll()
                                .antMatchers("lifepill/v1/branch-summary/**")
                                .hasRole(OWNER.name())
                                //.antMatchers("/lifepill/v1/admin/**").hasAnyRole(OWNER_READ.name(), CASHIER.name())
                                .antMatchers("/lifepill/v1/admin/**")
                                .hasRole(OWNER.name())
                                .antMatchers("/lifepill/v1/cashierNew/**")
                                .hasAnyRole(CASHIER.name(), MANAGER.name(), OWNER.name())
                                .antMatchers("lifepill/v1/branch/**")
                                .hasRole(OWNER.name())
                                .antMatchers("lifepill/v1/employers/**")
                                .hasAnyRole(OWNER.name(), MANAGER.name())
                                .antMatchers("lifepill/v1/cashier/**")
                                .hasAnyRole(CASHIER.name(), MANAGER.name(), OWNER.name())
                                .antMatchers("lifepill/v1/owner/**")
                                .hasRole(OWNER.name())
                                .antMatchers("lifepill/v1/branch-manager/**")
                                .hasAnyRole(MANAGER.name(), OWNER.name())
                                .antMatchers("/lifepill/v1/item-Category/**")
                                .hasAnyRole(OWNER.name(), MANAGER.name(), CASHIER.name())
                                .antMatchers("/lifepill/v1/item/**")
                                .hasAnyRole(OWNER.name(), MANAGER.name(), CASHIER.name())
                                .antMatchers("/lifepill/v1/order/**")
                                .hasAnyRole(OWNER.name(), MANAGER.name(), CASHIER.name())
                                .antMatchers("/lifepill/v1/supplierCompanies/**")
                                .hasAnyRole(OWNER.name(), MANAGER.name(), CASHIER.name())
                                .antMatchers("/lifepill/v1/supplier/**")
                                .hasAnyRole(OWNER.name(), MANAGER.name(), CASHIER.name())
                                .antMatchers("/lifepill/v1/session/**")
                                .hasAnyRole(OWNER.name(), MANAGER.name(), CASHIER.name())
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * CORS configuration source.
     *
     * @return CorsConfigurationSource object
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://44.218.33.250", "http://localhost:3000","http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}