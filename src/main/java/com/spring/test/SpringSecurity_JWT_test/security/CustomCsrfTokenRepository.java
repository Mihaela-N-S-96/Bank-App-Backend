package com.spring.test.SpringSecurity_JWT_test.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";
    private static final String CSRF_HEADER_NAME = "X-XSRF-TOKEN";
    private static final int CSRF_COOKIE_EXPIRATION_TIME = 5;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {

        CsrfToken token = new DefaultCsrfToken(CSRF_HEADER_NAME, CSRF_COOKIE_NAME,
                UUID.randomUUID().toString());
        System.out.println("generateToken-- "+ token);
        return token;
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        if (token == null) {
            response.addCookie(CookieUtil.create(CSRF_COOKIE_NAME, null, CSRF_COOKIE_EXPIRATION_TIME, false, false));
            return;
        }

        Cookie cookie = CookieUtil.create(CSRF_COOKIE_NAME, token.getToken(), CSRF_COOKIE_EXPIRATION_TIME, false, false);
        System.out.println("save token in cookie-- "+ cookie);
        response.addCookie(cookie);
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
         Cookie cookie = WebUtils.getCookie(request, CSRF_COOKIE_NAME);
        if (cookie == null) {
            return null;
        }
        System.out.println("load Token-- ");
        return new DefaultCsrfToken(CSRF_HEADER_NAME, CSRF_COOKIE_NAME, cookie.getValue());

    }
}
