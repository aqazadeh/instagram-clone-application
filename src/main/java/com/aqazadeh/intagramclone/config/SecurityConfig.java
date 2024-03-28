package com.aqazadeh.intagramclone.config;

import com.aqazadeh.intagramclone.filter.ServletExceptionFilterHandler;
import com.aqazadeh.intagramclone.filter.ServletSecurityFilterChain;
import com.aqazadeh.intagramclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 12.03.2024
 * Time: 21:49
 */
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final UserService userService;
    private final ServletSecurityFilterChain servletSecurityFilterChain;
    private final ServletExceptionFilterHandler servletExceptionFilterHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x-> x.requestMatchers("/api/v1/auth/change-password").authenticated())
                .authorizeHttpRequests(x-> x.requestMatchers("/api/v1/auth/**").anonymous())
                .authorizeHttpRequests(x -> x.requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll())
                .authorizeHttpRequests(x -> x.anyRequest().authenticated())
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(servletExceptionFilterHandler, LogoutFilter.class)
                .addFilterBefore(servletSecurityFilterChain, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
