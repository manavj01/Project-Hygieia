package com.hygieia.Project.Hygieia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Use lambda to disable CSRF (new style)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/register",
//                                "/login"
//                        )
//                        .permitAll()
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll() // Allow all requests for now
                )
                .httpBasic(basic -> {
                });
        return http.build();
    }

}
