package com.jtp.data.config;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.stream.Collectors;

@Component
public class RequestLoggingFilter extends org.springframework.web.filter.OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Log HTTP request method and URL
        System.out.println("Request URL: " + request.getMethod() + " " + request.getRequestURL());
        logger.debug("Request URL: " + request.getMethod() + " " + request.getRequestURL());

        // Log headers
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                System.out.println("Header: " + headerName + " = " + headerValue);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
