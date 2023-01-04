package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccount(Account account){
        String email = account.getUser().getEmail();

        User user = userRepository.findByEmail(email);
        account.setUser(user);

        account = accountRepository.save(account);
        return account;
    }

    public Optional<Account> findById(Long id){

        Optional<Account> account = accountRepository.findById(id);
        return account;
    }

    @Transactional
    public void updateSavingsAccount(Float savings, Long id){
        accountRepository.updateSavingsAccount(savings, id);
    }

    @Transactional
    public void decreasesWithdrawalFromBalance(Float withdrawal, Long id){
        accountRepository.decreasesWithdrawalFromBalance(withdrawal, id);
    }

}
