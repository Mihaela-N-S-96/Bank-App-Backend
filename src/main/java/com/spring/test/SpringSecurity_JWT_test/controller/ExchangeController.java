package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Exchange;
import com.spring.test.SpringSecurity_JWT_test.service.ExchangeService;
import com.spring.test.SpringSecurity_JWT_test.service.ExchangeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    @Autowired
    private ExchangeServiceImpl exchangeService;


    @PatchMapping("/exchange")
    public Exchange addExchange(@RequestBody Exchange exchange, @RequestParam Integer id){//id_account
//        exchange =exchange

        return  exchangeService.saveExchange(exchange, id);
    }
}
