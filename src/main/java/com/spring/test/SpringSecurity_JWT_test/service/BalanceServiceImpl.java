package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Balance;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    public boolean validateBalance(Balance balance, Double value){
        if(value<= balance.getBalance())
            return true;
        else return false;
    }

    @Transactional
    public Balance saveBalance(Balance balance, Integer id_account){
        Optional<Account> account =  accountRepository.findById(id_account);

        balance.setAccount(account.get());

        balance = balanceRepository.saveAndFlush(balance);
        if(balance.getStatus().contains("deposit"))
            accountRepository.addValueToBalance(balance.getBalance(), id_account);
        else
            if(balance.getStatus().contains("withdraw") && validateBalance(balance, balance.getBalance()))
                accountRepository.decreasesValueFromBalance(balance.getBalance(), id_account);
            else throw new RequestException("Forbidden");

            return new Balance(balance.getId(), balance.getBalance(), balance.getStatus(), balance.getDate(), balance.getDetails());
    }

    @Transactional
    public HashMap<String, Object> getBalanceResponse(Balance balance,
                                                      Integer id_account){

        Balance balanceObject = saveBalance(balance, id_account);

        HashMap<String, Object> balanceResponse = new HashMap<>();
        balanceResponse.put("balance", balanceObject);
        balanceResponse.put("status", balanceObject.getStatus());

        return  new HashMap<>(balanceResponse);
    }

    public List<Balance> getAllBalanceByAccountId(Integer id){

        return balanceRepository.getAllBalanceByAccountId(id);
    }
}
