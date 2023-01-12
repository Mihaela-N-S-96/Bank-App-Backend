package com.spring.test.SpringSecurity_JWT_test.exceptions.transfer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class TransferExceptionHandler {

    @ExceptionHandler(value = TransferRequestException.class)
    public ResponseEntity<Object> handleTransferException(TransferRequestException ex){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        TransferException transferException = new TransferException(
                ex.getMessage(),
                badRequest,
                ZonedDateTime.now());

        return new ResponseEntity<>(transferException, badRequest);
    }
}
