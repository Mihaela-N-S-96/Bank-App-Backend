package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class AccountServiceImpl implements AccountService{

    private   UserRepository userRepository;

    private   AccountRepository accountRepository;



    public AccountServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public boolean hasMoneyInAccount(Double value, Integer id_account){
        if(accountRepository.getAccountById(id_account).getBalance() < value)
            return false;
        return true;
    }

    @Transactional
    public Account saveAccount(Account account){

    try {
        String email = account.getUser().getEmail();
        User user = userRepository.findByEmail(email);
        if(user == null) throw new RequestException("This user can not be identify! Please check your credentials!");
        else {
            account.setUser(user);
            account = accountRepository.save(account);

            AccountSerializer accountObject = new AccountSerializer();

            return accountObject.deserializeObject(account);
        }
    }catch (RequestException e){
        throw new RequestException(e.getMessage());
    }
    catch (Exception e){
         throw new RequestException("This account can not be registered. Please check your request!");
    }
    }

    @Transactional
    public void addValueToSavings(Double value, Integer account_id){
        Account account = accountRepository.getAccountById(account_id);
        account.setSavings(account.getSavings() + value);

        accountRepository.save(account);
    }

    @Transactional
    public void decreasesValueFromSavings(Double value, Integer id_account){
        try {
            Account account = accountRepository.getAccountById(id_account);
            account.setSavings(account.getSavings() - value);

            accountRepository.save(account);
        }catch (Exception e){
            throw new RequestException("This withdraw can not be registered!");
        }
    }

    @Transactional
    public void decreasesValueFromBalance(Double value, Integer id){
        Account account = accountRepository.getAccountById(id);
        account.setBalance(account.getBalance() - value);

        accountRepository.save(account);
    }

    @Transactional
    public void updateTypeOfPlanByAccountId(Integer id_account, String type_of_plan){
    Account account = accountRepository.getAccountById(id_account);
    account.setType_of_plan(type_of_plan);

    accountRepository.save(account);
    }


    @Transactional
    public void increaseSavingsAccount(Double newValue, Integer id){
     Account account = accountRepository.getAccountById(id);
     account.setSavings(account.getSavings() + newValue);

     accountRepository.save(account);
    }

    @Transactional
    public void  addValueToBalance(Double value, Integer id_account){
    Account account = accountRepository.getAccountById(id_account);
    account.setBalance(account.getBalance() + value);

    accountRepository.save(account);

    }

    public List<Account> getAccountsByUserId(Integer id){
        return  accountRepository.findByUserId(id);
    }


    @Transactional
    public HashMap<String,Object> editTypeOfPlanByAccountId(Integer id_account, String type_of_plan){
        updateTypeOfPlanByAccountId(id_account,type_of_plan );

         Account account = accountRepository.findById(id_account).get();
         Account responseAccount = new Account(account.getId(), account.getCurrency(), account.getSavings(), account.getDeposit(), account.getBalance(), account.getType_of_plan(), account.getCreated_at());

         HashMap<String, Object> responseEntity = new HashMap<>();
         responseEntity.put("account", responseAccount);

         return responseEntity;
    }

    @Transactional
    public ResponseEntity<?> deleteByAccountId(Integer id){

        try {
            accountRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new RequestException("This account can not be deleted");
        }catch (Exception ex){
            throw new RequestException("Error delete. Internal error.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("This account was deleted with success");
    }

}
