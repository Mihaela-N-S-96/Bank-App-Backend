package com.spring.test.SpringSecurity_JWT_test.security.csrf;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CsrfTokenFilter extends OncePerRequestFilter {

    private static final String CSRF_TOKEN_HEADER_NAME = "X-XSRF-TOKEN";
    private static final String CSRF_TOKEN_ATTR_NAME = "_csrf";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CSRF_TOKEN_ATTR_NAME);
        if (csrfToken != null) {
            response.setHeader(CSRF_TOKEN_HEADER_NAME, csrfToken.getToken());
            HttpSession session = request.getSession();
            session.setAttribute(CSRF_TOKEN_ATTR_NAME, csrfToken);
        }
        filterChain.doFilter(request, response);
    }
}

