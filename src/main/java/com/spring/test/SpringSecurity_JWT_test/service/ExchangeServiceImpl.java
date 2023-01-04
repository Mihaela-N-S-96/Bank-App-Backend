package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Exchange;
import org.springframework.stereotype.Component;

@Component
public interface ExchangeServiceImpl {

    public Double changeFromEuroToLei(Double euro );
    public Double changeFromLeiToEuro(Double lei);
    public Exchange saveExchange(Exchange exchange, Integer id);
}
