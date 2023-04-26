package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.security.CustomCsrfTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    @Autowired
    private CustomCsrfTokenRepository csrfTokenRepository;

    protected boolean isCsrfTokenValid(HttpServletRequest request) {
        CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
//        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        System.out.println("csrfToken = "+ csrfToken.getToken());
        if (csrfToken == null) {
            System.out.println("csrfToken este  null ");
            return false;
        }
        String actualToken = request.getHeader("X-XSRF-TOKEN");
        System.out.println("Actual Token header = "+actualToken);
        if (actualToken == null) {
            System.out.println("Token from header is null ");
            return false;
        }
        return csrfToken.getToken().equals(actualToken);
    }


}
