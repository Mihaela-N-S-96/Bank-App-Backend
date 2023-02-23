package com.spring.test.SpringSecurity_JWT_test.service;


import com.spring.test.SpringSecurity_JWT_test.model.Account;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public interface AccountService {

     Optional<Account> findById(Integer id);
     Account saveAccount(Account account);
     void updateSavingsAccount(Double savings, Integer id);
     void decreasesValueFromBalance(Double withdrawal, Integer id);
     String getAccountType(Integer id);
     void  addValueToBalance(Double value, Integer id);
     List<Account> getAccountsByUserId(Integer id);
     HashMap<String,Object> editTypeOfPlanByAccountId(Integer id_account, String type_of_plan);
     void deleteByAccountId(Integer id_account);

}
