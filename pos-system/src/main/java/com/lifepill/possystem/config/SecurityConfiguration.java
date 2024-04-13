package com.lifepill.possystem.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


import static com.lifepill.possystem.entity.enums.Role.CASHIER;
import static com.lifepill.possystem.entity.enums.Role.OWNER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RequestMatcher requestMatcher = new AntPathRequestMatcher("/lifepill/v1/auth/register");
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(requestMatcher)
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .build();
    }
}