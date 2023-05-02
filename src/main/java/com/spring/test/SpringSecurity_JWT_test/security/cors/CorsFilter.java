package com.spring.test.SpringSecurity_JWT_test.security.cors;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CorsFilter implements CorsConfigurationSource {

    static final List<String> ALLOW_CORS_ORIGINS = new ArrayList<>(Arrays.asList("http://localhost:3000"));
    static final List<String> ALLOW_METHODS = new ArrayList<>(Arrays.asList("GET","POST", "PATCH","DELETE"));
    static final List<String> ALLOW_HEADERS = new ArrayList<>(Arrays.asList("Authorization", "Content-Type","X-XSRF-TOKEN","X-CSRF-TOKEN"));

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

        CorsConfiguration corsFilter = new CorsConfiguration();
        corsFilter.setAllowedOrigins(ALLOW_CORS_ORIGINS);
        corsFilter.setAllowCredentials(true);
        corsFilter.setAllowedMethods(ALLOW_METHODS);
        corsFilter.setAllowedHeaders(ALLOW_HEADERS);
        return corsFilter;
    }
}
