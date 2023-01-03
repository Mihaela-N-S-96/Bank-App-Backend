package com.spring.test.SpringSecurity_JWT_test.service;


import com.spring.test.SpringSecurity_JWT_test.model.Account;
import org.springframework.stereotype.Component;

@Component
public interface AccountService {

    public Account saveAccount(Account account);
    public void updateSavingsAccount(Float savings, Long id);
}
