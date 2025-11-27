package com.lifepill.possystem.config;

import com.lifepill.possystem.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.lifepill.possystem.entity.enums.Role.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Configuration class for Spring Security.
 *
 * <p>CORS is enabled using Spring Security's built-in support with
 * {@code Customizer.withDefaults()}, which automatically picks up the
 * {@link org.springframework.web.cors.CorsConfigurationSource} bean
 * from {@link CorsConfig}.</p>
 *
 * @author LifePill Development Team
 * @version 1.0
 * @see CorsConfig
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;

    /**
     * Configures the security filter chain.
     *
     * <p>Security features:</p>
     * <ul>
     *   <li>CSRF disabled (stateless REST API)</li>
     *   <li>CORS enabled with configuration from {@link CorsConfig}</li>
     *   <li>JWT-based stateless authentication</li>
     *   <li>Role-based access control</li>
     * </ul>
     *
     * @param http HttpSecurity object
     * @return SecurityFilterChain object
     * @throws Exception If an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // Uses CorsConfigurationSource from CorsConfig
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
}