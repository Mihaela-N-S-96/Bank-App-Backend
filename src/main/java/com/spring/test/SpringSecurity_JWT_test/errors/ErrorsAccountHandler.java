package com.spring.test.SpringSecurity_JWT_test.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsAccountHandler {

    private final Logger log = LoggerFactory.getLogger(ErrorsAccountHandler.class);

    @ExceptionHandler(AccountNotFoundException.class)
    public void handleAccountNotFound(AccountNotFoundException ex){
        log.error("Can not find account with this id!", ex.getMessage(), ex);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFound(UserNotFoundException ex){
        log.error("Can not find user with this email!", ex.getMessage(), ex);
    }
}
