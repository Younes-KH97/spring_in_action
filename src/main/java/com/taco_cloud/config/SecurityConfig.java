package com.taco_cloud.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers(HttpMethod.OPTIONS).permitAll()
//                            .requestMatchers(HttpMethod.POST, "/api/ingredients").permitAll()
//                            .requestMatchers("/api/tacos", "/api/orders/**").permitAll()
//                            .requestMatchers(HttpMethod.PATCH, "/api/ingredients").permitAll()
//                            .requestMatchers("/**").permitAll())
//                .formLogin()
//                .loginPage("/login")
//
//                .and()
//                .httpBasic()
//                .realmName("Taco Cloud")
//
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//
//                .and()
//                .csrf()
//                .ignoringRequestMatchers("/h2-console/**", "/api/**")
//
//                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin().and().build();
        return http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configure(http))
                .build();
    }
}
