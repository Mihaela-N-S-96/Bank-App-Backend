package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public Account addAccount(@RequestBody Account account){
        User user = account.getUser();
        account.setUser(user);
        return accountService.saveAccount(account);
    }

    @PatchMapping("/savings")
    public void addToSavings(@RequestParam Float savings, @RequestBody User user){

    }

}
