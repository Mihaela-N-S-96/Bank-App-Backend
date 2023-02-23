package com.spring.test.SpringSecurity_JWT_test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    @Transactional
    public Account saveAccount(Account account){

try {
    String email = account.getUser().getEmail();
    User user = userRepository.findByEmail(email);
    account.setUser(user);
   account = accountRepository.save(account);

    System.out.println(account.getCreated_at());
    if(account == null)System.out.println("asta e null");
    System.out.println("Account din saving "+account.toString());
    return account;
}catch (Exception e){
    throw new RuntimeException("BAM");
}

    }

    public Optional<Account> findById(Integer id){

        Optional<Account> account = accountRepository.findById(id);
        return account;
    }

    @Transactional
    public void addValueToSavings(Double value, Integer account_id){
        Account account = findById(account_id).get();
        account.setSavings(account.getSavings() + value);

        accountRepository.save(account);
    }

    @Transactional
    public void decreasesValueFromBalance(Double value, Integer id){
        Account account = findById(id).get();
        account.setBalance(account.getBalance() - value);

        accountRepository.save(account);
    }

    @Transactional
    public void updateSavingsAccount(Double savings, Integer id){
        accountRepository.updateSavingsAccount(savings, id);
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

    @Transactional
    public HashMap<String,Object> editTypeOfPlanByAccountId(Integer id_account, String type_of_plan){
         accountRepository.editTypeOfPlanByAccountId(id_account,type_of_plan );

         Account account = accountRepository.findById(id_account).get();
         Account responseAccount = new Account(account.getId(), account.getCurrency(), account.getSavings(), account.getDeposit(), account.getBalance(), account.getType_of_plan(), account.getCreated_at());

         HashMap<String, Object> responseEntity = new HashMap<>();
         responseEntity.put("account", responseAccount);

         return responseEntity;
    }

    @Transactional
    public void deleteByAccountId(Integer id_account){

        accountRepository.deleteByIdWithCascade(id_account);
    }

}
