package com.spring.test.SpringSecurity_JWT_test.controller;

import org.springframework.security.web.csrf.CsrfToken;
import javax.servlet.http.HttpSession;

public class CsrfController {
    protected static final String CSRF_TOKEN_ATTR_NAME = "_csrf";
    public void validateCsrfToken(String csrfToken,
                                               HttpSession session) throws Exception{

        CsrfToken sessionToken = (CsrfToken) session.getAttribute(CSRF_TOKEN_ATTR_NAME);

        if (!sessionToken.getToken().equals(csrfToken)) {
            throw new Exception("Invalid CSRF token");
        }
    }
}
