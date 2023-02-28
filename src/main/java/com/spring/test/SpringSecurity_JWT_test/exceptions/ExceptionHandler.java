package com.spring.test.SpringSecurity_JWT_test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleException(RequestException e){
        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        Exception signinException = new Exception(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now());

        return new ResponseEntity<>(signinException, badRequest);
    }
}
