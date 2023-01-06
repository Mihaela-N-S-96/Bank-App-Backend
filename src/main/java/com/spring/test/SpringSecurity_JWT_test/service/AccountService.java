package com.spring.test.SpringSecurity_JWT_test.service;


import com.spring.test.SpringSecurity_JWT_test.model.Account;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AccountService {

    public Optional<Account> findById(Integer id);
        public Account saveAccount(Account account);
    public void updateSavingsAccount(Double savings, Integer id);
    public void decreasesValueFromBalance(Double withdrawal, Integer id);
    public String getAccountType(Integer id);
    public void  addValueToBalance(Double value, Integer id);
}
