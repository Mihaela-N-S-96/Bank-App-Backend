package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.DepositRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;



class DepositServiceImplTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private DepositServiceImpl depositService;


    @Test
    void returnFalseForValidateDeposit() {
        DepositServiceImpl deposit = new DepositServiceImpl(accountRepository,depositRepository);
        Account account = new Account(1, "ron",30.0, 200.0,3000.0, "gold", new Date(2020-01-01));

        assertFalse(deposit.validateDeposit(account, 500.0));

    }

    @Test
    void returnTrueForValidateDeposit() {
        DepositServiceImpl deposit = new DepositServiceImpl(accountRepository,depositRepository);
        Account account = new Account(1, "ron",30.0, 200.0,3000.0, "gold", new Date(2020-01-01));

        assertEquals(true, deposit.validateDeposit(account, 100.0));
    }


}