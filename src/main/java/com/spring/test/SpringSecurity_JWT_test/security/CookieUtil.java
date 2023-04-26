package com.spring.test.SpringSecurity_JWT_test.security;

import javax.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie create(String name, String value, int maxAge, boolean isSecure, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(isSecure);
        cookie.setHttpOnly(httpOnly);
        cookie.setPath("/");
        System.out.println("cookie= "+cookie);
        return cookie;
    }

}
