package com.spring.test.SpringSecurity_JWT_test.exceptions.signin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class SigninExceptionHandler {

    @ExceptionHandler(value = {SigninRequestException.class})
    public ResponseEntity<Object> handleSigninException(SigninRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        SigninException signinException = new SigninException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now());

        return new ResponseEntity<>(signinException, badRequest);
    }
}
