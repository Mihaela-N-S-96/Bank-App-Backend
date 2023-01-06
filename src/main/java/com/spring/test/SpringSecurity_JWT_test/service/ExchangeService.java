package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Exchange;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeService implements ExchangeServiceImpl{

    public static Double EURO_VALUE = 5.00;


    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private AccountRepository accountRepository;


    public Double changeFromEuroToLei(Double euro){
        BigDecimal bd = new BigDecimal(euro * EURO_VALUE).setScale(2, RoundingMode.HALF_UP);
        Double result = bd.doubleValue();
        return result;
    }

    public Double changeFromLeiToEuro(Double lei){
        BigDecimal bd = new BigDecimal(lei / EURO_VALUE).setScale(2, RoundingMode.HALF_UP);
        Double result = bd.doubleValue();
        return result;
    }

    @Transactional
    public Exchange saveExchange(Exchange exchange, Integer id){

        Account account = accountRepository.findById(id).get();
        Integer user_id = account.getUser_id();
        System.out.println(account.getCurrency());

        if(account.getCurrency().equals("euro")){
            Double lei = changeFromEuroToLei(exchange.getExchange());
            System.out.println("lei= "+ lei);
            accountRepository.decreasesValueFromBalance(exchange.getExchange(),id);

            Integer id_account = accountRepository.findByIdAndTypeOfPlan(user_id, "lei");//, "lei"
//Long id_account = accountRepository.findByIdAndTypeOfPlan(id).getId();
            accountRepository.addValueToBalance(lei, id_account);

        }else
            if(account.getCurrency().equals("lei")){
                System.out.println("aici = "+ changeFromLeiToEuro(20.0));
            Double euro = changeFromLeiToEuro(exchange.getExchange());
            System.out.println("euro= "+ euro);
            accountRepository.decreasesValueFromBalance(exchange.getExchange(),id);

            Integer id_account = accountRepository.findByIdAndTypeOfPlan(user_id, "euro");//, "euro"
//                Long id_account = accountRepository.findByIdAndTypeOfPlan(id).getId();
                System.out.println("id_account= "+ id_account);
            accountRepository.addValueToBalance(euro, id_account);
            }

            exchange.setAccount(account);
            exchange = exchangeRepository.save(exchange);

          return exchange;
    }


}
