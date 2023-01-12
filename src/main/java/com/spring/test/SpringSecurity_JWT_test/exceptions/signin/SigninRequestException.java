package com.spring.test.SpringSecurity_JWT_test.exceptions.signin;

public class SigninRequestException extends RuntimeException{

    public SigninRequestException (String message){
        super(message);
    }
}
