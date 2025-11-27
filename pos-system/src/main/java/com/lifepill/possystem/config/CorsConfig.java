package com.lifepill.possystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CORS (Cross-Origin Resource Sharing) Configuration.
 *
 * <p>This configuration class provides a {@link CorsConfigurationSource} bean
 * that is used by Spring Security's built-in CORS support. All settings are
 * loaded from environment variables via application.yml.</p>
 *
 * <h3>Environment Variables:</h3>
 * <table border="1">
 *   <tr><th>Variable</th><th>Description</th><th>Example</th></tr>
 *   <tr><td>CORS_ALLOWED_ORIGINS</td><td>Comma-separated allowed origins</td><td>http://localhost:3000,http://localhost:5173</td></tr>
 *   <tr><td>CORS_ALLOWED_METHODS</td><td>Comma-separated HTTP methods</td><td>GET,POST,PUT,DELETE,OPTIONS</td></tr>
 *   <tr><td>CORS_ALLOWED_HEADERS</td><td>Comma-separated allowed headers</td><td>Authorization,Content-Type</td></tr>
 *   <tr><td>CORS_ALLOW_CREDENTIALS</td><td>Allow credentials (true/false)</td><td>true</td></tr>
 *   <tr><td>CORS_MAX_AGE</td><td>Preflight cache duration (seconds)</td><td>3600</td></tr>
 * </table>
 *
 * <h3>Usage:</h3>
 * <p>This bean is automatically picked up by Spring Security when
 * {@code .cors(Customizer.withDefaults())} is configured in the security filter chain.</p>
 *
 * @author LifePill Development Team
 * @version 1.0
 * @see CorsConfigurationSource
 * @see SecurityConfiguration
 */
@Configuration
public class CorsConfig {

    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${cors.allowed-methods}")
    private String allowedMethods;

    @Value("${cors.allowed-headers}")
    private String allowedHeaders;

    @Value("${cors.allow-credentials}")
    private boolean allowCredentials;

    @Value("${cors.max-age:3600}")
    private long maxAge;

    /**
     * Creates and configures the CORS configuration source.
     *
     * <p>This bean is automatically used by Spring Security's CORS filter
     * when CORS is enabled in the security configuration.</p>
     *
     * @return CorsConfigurationSource configured with environment-based settings
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Parse and set allowed origins from environment
        List<String> origins = Arrays.stream(allowedOrigins.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        configuration.setAllowedOrigins(origins);

        // Parse and set allowed methods from environment
        List<String> methods = Arrays.stream(allowedMethods.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        configuration.setAllowedMethods(methods);

        // Parse and set allowed headers from environment
        List<String> headers = Arrays.stream(allowedHeaders.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        configuration.setAllowedHeaders(headers);

        // Set credentials support
        configuration.setAllowCredentials(allowCredentials);

        // Set preflight response cache duration
        configuration.setMaxAge(maxAge);

        // Set headers that clients are allowed to access
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Disposition"));

        // Register configuration for all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
