/*
package com.lifepill.possystem.filter;

import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Allow requests from http://localhost:3000
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        // Allow specific HTTP methods
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        // Allow specific HTTP headers
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        // Allow credentials
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // Handle preflight requests
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Pass the request down the filter chain
            filterChain.doFilter(request, response);
        }
    }
}
*/
