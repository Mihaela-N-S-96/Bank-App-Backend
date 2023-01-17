package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Exchange;
import com.spring.test.SpringSecurity_JWT_test.service.ExchangeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    @Autowired
    private ExchangeServiceImpl exchangeService;


    @PatchMapping("/exchange")
    public ResponseEntity<Object> addExchange(@RequestBody Exchange exchange, @RequestParam Integer id){//id_account


        return new ResponseEntity<>(exchangeService.getExchangeResponse(exchange,id), HttpStatus.OK);

    }
}
