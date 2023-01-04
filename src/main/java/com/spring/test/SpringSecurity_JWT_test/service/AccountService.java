package com.spring.test.SpringSecurity_JWT_test.service;


import com.spring.test.SpringSecurity_JWT_test.model.Account;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AccountService {

    public Optional<Account> findById(Long id);
        public Account saveAccount(Account account);
    public void updateSavingsAccount(Float savings, Long id);
    public void decreasesWithdrawalFromBalance(Float withdrawal, Long id);
}
