package com.findtripmate.modules.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // disable csrf for APIs

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // ✅ allow auth
                .requestMatchers("/users/**").permitAll() //allow users
                .requestMatchers("/h2-console/**").permitAll() // ✅ allow H2
                .anyRequest().authenticated()
            )

            .headers(headers -> headers
                .frameOptions(frame -> frame.disable()) // ✅ allow H2 console
            );

        return http.build();
    }
}