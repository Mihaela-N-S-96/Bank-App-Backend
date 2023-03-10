package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Exchange;
import com.spring.test.SpringSecurity_JWT_test.repository.ExchangeRepository;
import com.spring.test.SpringSecurity_JWT_test.service.ExchangeServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/exchanges")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class ExchangeController {

    private final ExchangeServiceImpl exchangeService;

    public ExchangeController(ExchangeServiceImpl exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/")
    public ArrayList<Exchange> getAllExchanges(@RequestParam Integer id){

        return  exchangeService.getExchangeList(id);
    }

    @PostMapping("/exchange")
    public ResponseEntity<Object> addExchange(@RequestBody Exchange exchange, @RequestParam Integer id){//id_account


        return new ResponseEntity<>(exchangeService.getExchangeResponse(exchange,id), HttpStatus.OK);

    }
}
