package com.example.GCS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Allow all origins
        config.addAllowedMethod("GET"); // Allow GET requests
        config.addAllowedHeader("Content-Type"); // Allow Content-Type header

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply the configuration to all paths
        return source;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter(corsConfigurationSource());
        return filter;
    }

}
