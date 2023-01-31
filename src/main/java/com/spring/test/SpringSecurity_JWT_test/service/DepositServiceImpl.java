package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Deposit;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Optional;

@Service
public class DepositServiceImpl implements DepositService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DepositRepository depositRepository;


    public boolean validateDeposit(Account account, Double value){
        if(value<= account.getDeposit())
            return true;
        else return false;
    }

    @Transactional
    public Deposit saveDeposit(Deposit deposit, Integer id){
        Optional<Account> account = accountRepository.findById(id);

        deposit.setAccount(account.get());

        deposit = depositRepository.save(deposit);
        if(deposit.getStatus().contains("deposit"))
        accountRepository.addValueToDeposit(deposit.getTransfer(), id);
        else
            if(deposit.getStatus().contains("withdraw") && validateDeposit(account.get(), deposit.getTransfer()))
                accountRepository.decreasesValueFromDeposit(deposit.getTransfer(), id);
            else throw new RequestException("Forbidden");
            return new Deposit(deposit.getId(), deposit.getTransfer(), deposit.getStatus(), deposit.getDate(), deposit.getDetails());
    }

    @Transactional
    public HashMap<String, Object> getDepositResponse(Deposit deposit,
                                                      Integer id_account){


        Deposit depositObject = saveDeposit(deposit, id_account);

        HashMap<String, Object> depositResponse = new HashMap<>();
        depositResponse.put("deposit", depositObject);
        depositResponse.put("status", depositObject.getStatus());

        return new HashMap<>(depositResponse);

    }
}
