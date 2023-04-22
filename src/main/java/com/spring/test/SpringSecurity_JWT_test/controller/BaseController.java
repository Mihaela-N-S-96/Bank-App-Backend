package com.spring.test.SpringSecurity_JWT_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

//    @Autowired
//    private CsrfTokenRepository csrfTokenRepository;
//
//    protected boolean isCsrfTokenValid(HttpServletRequest request) {
//        CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
//        if (csrfToken == null) {
//            return false;
//        }
//        String actualToken = request.getHeader("X-XSRF-TOKEN");
//        System.out.println(actualToken);
//        if (actualToken == null) {
//            return false;
//        }
//        return csrfToken.getToken().equals(actualToken);
//    }

    @Autowired
    private CookieCsrfTokenRepository csrfTokenRepository;

    protected boolean isCsrfTokenValid(HttpServletRequest request) {
        CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
        if (csrfToken == null) {
            return false;
        }
        String actualToken = request.getHeader("X-XSRF-TOKEN");
        if (actualToken == null) {
            return false;
        }
        return csrfToken.getToken().equals(actualToken);
    }

}
