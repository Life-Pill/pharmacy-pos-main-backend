package com.lifepill.possystem.config;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Supplier;

@FunctionalInterface
public interface CsrfTokenRequestHandler extends CsrfTokenRequestResolver {
    void handle(HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> csrfToken);

    default String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
        Assert.notNull(request, "request cannot be null");
        Assert.notNull(csrfToken, "csrfToken cannot be null");
        String actualToken = request.getHeader(csrfToken.getHeaderName());
        if (actualToken == null) {
            actualToken = request.getParameter(csrfToken.getParameterName());
        }

        return actualToken;
    }
}
