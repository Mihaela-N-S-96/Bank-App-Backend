package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Exchange;
import com.spring.test.SpringSecurity_JWT_test.service.ExchangeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static com.spring.test.SpringSecurity_JWT_test.controller.AuthController.CSRF_TOKEN_HEADER_NAME;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController extends CsrfController{

    private final ExchangeServiceImpl exchangeService;

    public ExchangeController(ExchangeServiceImpl exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/")
    public ArrayList<Exchange> getAllExchanges(@RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                               HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return  exchangeService.getExchangeList(id);
    }

    @PostMapping("/exchange")
    public ResponseEntity<Object> addExchange(@RequestBody Exchange exchange, @RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                              HttpSession session) throws Exception{//id_account

        validateCsrfToken(csrfToken, session);
        return new ResponseEntity<>(exchangeService.getExchangeResponse(exchange,id), HttpStatus.OK);

    }
}
