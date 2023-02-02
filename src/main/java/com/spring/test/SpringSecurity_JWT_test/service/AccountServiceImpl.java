package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.apache.naming.SelectorContext.prefix;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public String generateUniqueCode(){
        Random rand = new Random();

        long x = (long)(rand.nextDouble()*100000000000000L);

        String code = String.valueOf(prefix) + String.format("%014d", x);
        return code;
    }

    public Account saveAccount(Account account){
        String email = account.getUser().getEmail();

        User user = userRepository.findByEmail(email);
        account.setUser(user);

        account.setUnique(generateUniqueCode());
        account = accountRepository.save(account);
        return account;
    }

    public Optional<Account> findById(Integer id){

        Optional<Account> account = accountRepository.findById(id);
        return account;
    }

    @Transactional
    public void updateSavingsAccount(Double savings, Integer id){
        accountRepository.updateSavingsAccount(savings, id);
    }

    @Transactional
    public void decreasesValueFromBalance(Double value, Integer id){
        accountRepository.decreasesValueFromBalance(value, id);
    }
    @Transactional
    public void  addValueToBalance(Double value, Integer id){
        accountRepository.addValueToBalance(value, id);
    }
    public String getAccountType(Integer id){

        Account account = new Account();
        account = findById(id).get();

        return account.getCurrency();
    }

    public List<Account> getAccountsByUserId(Integer id){
        return  accountRepository.findByUserId(id);
    }


}
