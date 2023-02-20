package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Exchange;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

@Service
public class ExchangeService implements ExchangeServiceImpl{

    public static Double EURO_VALUE = 5.23;


    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountService;


    public Double changeFromEuroToLei(Double euro){
        BigDecimal bd = new BigDecimal(euro * EURO_VALUE).setScale(2, RoundingMode.HALF_UP);
        Double result = bd.doubleValue();
        return result;
    }

    public Double changeFromLeiToEuro(Double ron){
        BigDecimal bd = new BigDecimal(ron / EURO_VALUE).setScale(2, RoundingMode.HALF_UP);
        Double result = bd.doubleValue();
        return result;
    }

    @Transactional
    public void decreasesAndAddValueToBalance(Exchange exchange, Integer current_account,
                                              Integer user_id,String typeOfExchange,
                                              String changeToCurrency, Double value){

        Integer id_account;
       // accountRepository.decreasesValueFromBalance(exchange.getExchange(), current_account);
        accountService.decreasesValueFromBalance(exchange.getExchange(), current_account);
        if(accountRepository.findByIdAndTypeOfPlan(user_id, changeToCurrency) == null)
            throw new RequestException("The "+typeOfExchange+" exchange cannot be executed! The user does not have a "+changeToCurrency+" account.");
        id_account = accountRepository.findByIdAndTypeOfPlan(user_id, changeToCurrency);

        accountRepository.addValueToBalance(value, id_account);
    }

    @Transactional
    public Exchange saveExchange(Exchange exchange, Integer current_account){

        Account account = accountRepository.findById(current_account).get();
        Integer user_id = account.getUser_id();
        String typeOfExchange = "";

        if(account.getCurrency().equals("euro")){
            typeOfExchange = "EuroToRon";
            Double lei = changeFromEuroToLei(exchange.getExchange());
            //decreases the value from current account and add it to the exchangeAccount
            decreasesAndAddValueToBalance(exchange,current_account,user_id,typeOfExchange,"ron",lei);
        }else
            if(account.getCurrency().equals("ron")){
            typeOfExchange = "RonToEuro";
            Double euro = changeFromLeiToEuro(exchange.getExchange());
            //decreases the value from current account and add it to the exchangeAccount
            decreasesAndAddValueToBalance(exchange,current_account,user_id,typeOfExchange,"euro",euro);
            }

            exchange.setAccount(account);
            exchange.setType_exchange(typeOfExchange);
            exchange = exchangeRepository.save(exchange);


          return new Exchange(exchange.getId(), exchange.getExchange(),exchange.getType_exchange(), exchange.getDate(), exchange.getDetails());
    }
    @Transactional
    public HashMap<String, Object> getExchangeResponse(Exchange exchange,
                                                       Integer id_account){

        Exchange exchange1 = saveExchange(exchange, id_account);
        Double rate = EURO_VALUE;

        HashMap<String, Object> exchangeResponse = new HashMap<>();
        exchangeResponse.put("exchange", exchange1);
        exchangeResponse.put("rate", rate);

        return new HashMap<>(exchangeResponse);
    }


}
