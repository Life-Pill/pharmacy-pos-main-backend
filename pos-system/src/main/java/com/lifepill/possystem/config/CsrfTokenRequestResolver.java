package com.lifepill.possystem.config;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface CsrfTokenRequestResolver {
    String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken);
}
