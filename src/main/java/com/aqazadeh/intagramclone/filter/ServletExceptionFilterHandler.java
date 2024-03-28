package com.aqazadeh.intagramclone.filter;

import com.aqazadeh.intagramclone.exception.ApplicationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 12.03.2024
 * Time: 22:09
 */

@Component
public class ServletExceptionFilterHandler extends OncePerRequestFilter {
    private final HandlerExceptionResolver resolver;
    public ServletExceptionFilterHandler(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | MalformedJwtException | ApplicationException e) {
            resolver.resolveException(request, response, null, e);
        }
    }
}
