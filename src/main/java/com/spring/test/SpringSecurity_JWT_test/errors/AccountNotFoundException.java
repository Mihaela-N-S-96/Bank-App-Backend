package com.spring.test.SpringSecurity_JWT_test.errors;




public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message){
        super(message);
    }

    public AccountNotFoundException(){}


}
