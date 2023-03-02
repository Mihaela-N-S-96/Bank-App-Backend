package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static org.apache.naming.SelectorContext.prefix;

@Transactional
@Service
public class AccountServiceImpl implements AccountService{

//    @Autowired
    private final UserRepository userRepository;

//    @Autowired
    private final AccountRepository accountRepository;

    public AccountServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public String generateUniqueCode(){
        Random rand = new Random();

        long x = (long)(rand.nextDouble()*100000000000000L);

        String code = String.valueOf(prefix) + String.format("%014d", x);
        return code;
    }


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

    public Optional<Account> findById(Integer id){

        Optional<Account> account = accountRepository.findById(id);
        return account;
    }


    public void addValueToSavings(Double value, Integer account_id){
        Account account = findById(account_id).get();
        account.setSavings(account.getSavings() + value);

        accountRepository.save(account);
    }


    public void decreasesValueFromBalance(Double value, Integer id){
        Account account = findById(id).get();
        account.setBalance(account.getBalance() - value);

        accountRepository.save(account);
    }


    public void updateSavingsAccount(Double savings, Integer id){
        accountRepository.updateSavingsAccount(savings, id);
    }


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


    public HashMap<String,Object> editTypeOfPlanByAccountId(Integer id_account, String type_of_plan){
         accountRepository.editTypeOfPlanByAccountId(id_account,type_of_plan );

         Account account = accountRepository.findById(id_account).get();
         Account responseAccount = new Account(account.getId(), account.getCurrency(), account.getSavings(), account.getDeposit(), account.getBalance(), account.getType_of_plan(), account.getCreated_at());

         HashMap<String, Object> responseEntity = new HashMap<>();
         responseEntity.put("account", responseAccount);

         return responseEntity;
    }


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
