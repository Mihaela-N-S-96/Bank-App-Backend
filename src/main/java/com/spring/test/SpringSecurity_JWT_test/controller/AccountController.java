package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Balance;
import com.spring.test.SpringSecurity_JWT_test.service.AccountService;
import com.spring.test.SpringSecurity_JWT_test.service.BalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accounts")
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE},
//        allowCredentials = "true", allowedHeaders = {"Content-Type", "Authorization", "X-XSRF-TOKEN","X-CSRF-TOKEN"})
public class AccountController {


    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public List<Account> getAllAccountsByUserId(@RequestParam Integer id){
        return accountService.getAccountsByUserId(id);
    }

    @PostMapping("/account")
    public Account addAccount(@RequestBody Account account){

        return accountService.saveAccount(account);
    }

    @PostMapping("/savings")
    public void addToSavings(@RequestParam Double savings, @RequestParam Integer id){
        accountService.increaseSavingsAccount(savings, id);
    }


    @PatchMapping("/edit")
    public ResponseEntity<Object> editAccount(@RequestParam Integer id, @RequestParam String type){

        return new ResponseEntity<>(accountService.editTypeOfPlanByAccountId(id, type), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestParam Integer id){

        ResponseEntity response;
        try{
            response = accountService.deleteByAccountId(id);
        }catch (Exception e){
            throw new RequestException(e.getMessage());
        }

        return response;
    }

}
