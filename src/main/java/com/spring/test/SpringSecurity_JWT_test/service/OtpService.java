package com.spring.test.SpringSecurity_JWT_test.service;

public interface OtpService {
    public String generateOTP();
    public void clearOTP(String email);
}
