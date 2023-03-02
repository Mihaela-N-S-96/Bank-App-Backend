package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Exchange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public interface ExchangeServiceImpl {

    public Double changeFromEuroToLei(Double euro );
    public Double changeFromLeiToEuro(Double lei);
    public Exchange saveExchange(Exchange exchange, Integer id);
    HashMap<String, Object> getExchangeResponse(Exchange exchange,
                                                Integer id_account);

    public ArrayList<Exchange> getExchangeList(Integer id_account);
}
