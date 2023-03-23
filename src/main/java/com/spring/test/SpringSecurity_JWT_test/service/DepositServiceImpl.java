package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Deposit;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;

import com.spring.test.SpringSecurity_JWT_test.repository.DepositRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;


@Service
public class DepositServiceImpl implements DepositService{


    private final AccountRepository accountRepository;
    private final DepositRepository depositRepository;

    public DepositServiceImpl(  AccountRepository accountRepository, DepositRepository depositRepository) {
        this.accountRepository = accountRepository;
        this.depositRepository = depositRepository;
    }

    public boolean validateDeposit(Account account, Double value){
        if(value<= account.getDeposit())
            return true;
        else return false;
    }

    @Transactional
    public void increaseDeposit_AccountEntity(Double value, Integer account_id) {
        Account account = accountRepository.findById(account_id).get();

        account.setDeposit(account.getDeposit() + value);
        accountRepository.save(account);
    }

    @Transactional
    public void decreaseDeposit_AccountEntity(Double value, Integer account_id) {
        Account account = accountRepository.findById(account_id).get();

        account.setDeposit(account.getDeposit() - value);
        accountRepository.save(account);
    }

    @Transactional
    public void updateDepositFromAccount(Deposit deposit,Account account){
        if(deposit.getStatus().contains("deposit"))
            increaseDeposit_AccountEntity(deposit.getTransfer(), account.getId());
        else
        if(deposit.getStatus().contains("withdraw") && validateDeposit(account, deposit.getTransfer()))
            decreaseDeposit_AccountEntity(deposit.getTransfer(), account.getId());
        else throw new RequestException("Forbidden");
    }

    @Transactional
    public Deposit saveDeposit(Deposit deposit, Integer id){
       Account account = accountRepository.getAccountById(id);

        deposit.setAccount(account);
        deposit = depositRepository.save(deposit);

        updateDepositFromAccount(deposit, account);

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

    public ArrayList<Deposit> getDepositList(Integer id_account){

        return depositRepository.getAllDepositsByAccountId(id_account);
    }
}
