package com.example.paysonix.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class TokenValidationFilter extends OncePerRequestFilter {

    @Value("${app.token}")
    private String appToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Token");

        if (token == null || !token.equals(appToken)) {
            log.info("Incorrect token");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        filterChain.doFilter(request, response);
    }
}
