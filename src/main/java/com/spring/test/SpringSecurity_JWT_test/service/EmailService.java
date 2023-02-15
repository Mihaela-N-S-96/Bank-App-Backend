package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.payload.request.SignupRequest;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    public void sendOTPEmail(SignupRequest user, String OTP) throws UnsupportedEncodingException, MessagingException;
}
