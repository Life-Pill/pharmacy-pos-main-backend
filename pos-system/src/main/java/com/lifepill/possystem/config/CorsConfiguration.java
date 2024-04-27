package com.lifepill.possystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for CORS settings.
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    /**
     * Configures CORS mappings to allow requests from any origin and specified HTTP methods and headers.
     *
     * @param registry CorsRegistry instance
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/lifepill/v1/**")
                .allowedOrigins("*") // Allow requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specified HTTP methods
                .allowedHeaders("*"); // Allow all headers
    }
}

