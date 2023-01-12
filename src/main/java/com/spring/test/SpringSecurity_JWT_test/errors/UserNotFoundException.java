package com.spring.test.SpringSecurity_JWT_test.errors;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }
    public UserNotFoundException(){}
}
