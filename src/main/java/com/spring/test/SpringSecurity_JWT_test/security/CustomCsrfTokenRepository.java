//package com.spring.test.SpringSecurity_JWT_test.security;
//
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.DefaultCsrfToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.WebUtils;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.UUID;
//
//@Component
//public class CustomCsrfTokenRepository implements CsrfTokenRepository {
//
//    private static final String CSRF_TOKEN_ATTR_NAME = "_csrf";
//
//    @Override
//    public CsrfToken generateToken(HttpServletRequest request) {
//        HttpSession session = request.getSession(true);
//        CsrfToken token = (CsrfToken) session.getAttribute(CSRF_TOKEN_ATTR_NAME);
//        if (token == null) {
//            token = new DefaultCsrfToken("X-CSRF-Token", "csrf_token", UUID.randomUUID().toString());
//           try {
//               session.setAttribute(CSRF_TOKEN_ATTR_NAME, token);
//           }catch (Exception e){
//
//           }
//        }
//        System.out.println(" generateToken = "+ token.getToken());
//        return token;
//    }
//
//    @Override
//    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
//        // Do nothing
//    }
//
//    @Override
//    public CsrfToken loadToken(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return null;
//        }
//        return (CsrfToken) session.getAttribute(CSRF_TOKEN_ATTR_NAME);
//    }
//}
