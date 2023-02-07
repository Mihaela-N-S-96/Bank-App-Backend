package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Balance;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.service.AccountService;
import com.spring.test.SpringSecurity_JWT_test.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/")
    public List<Account> getAllAccountsByUserId(@RequestParam Integer id){
        return accountService.getAccountsByUserId(id);
    }

    @PostMapping("/account")
    public Account addAccount(@RequestBody Account account){
        User user = account.getUser();
        account.setUser(user);
        return accountService.saveAccount(account);
    }

    @PostMapping("/savings")
    public void addToSavings(@RequestParam Double savings, @RequestParam Integer id){
        accountService.updateSavingsAccount(savings, id);
    }

    @PostMapping("/deposit/balance")
    public ResponseEntity<Object> addToBalance(@RequestBody Balance balance,
                                               @RequestParam Integer id){

//        System.out.println(balance);
        return new ResponseEntity<>(balanceService.getBalanceResponse(balance, id),
                                                                      HttpStatus.OK);
    }

    @GetMapping("/deposit/balance")
    public List<Balance> addToBalance(@RequestParam Integer id){

        return balanceService.getAllBalanceByAccountId(id);
    }

    @PatchMapping("/edit")
    public ResponseEntity<Object> editAccount(@RequestParam Integer id, @RequestParam String type){

        return new ResponseEntity<>(accountService.editTypeOfPlanByAccountId(id, type), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam Integer id){

        accountService.deleteByAccountId(id);
        return new ResponseEntity<>("Delete", HttpStatus.OK);
    }

}
