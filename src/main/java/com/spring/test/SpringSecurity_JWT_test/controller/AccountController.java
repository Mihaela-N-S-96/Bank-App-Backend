package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Balance;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.service.AccountSerializer;
import com.spring.test.SpringSecurity_JWT_test.service.AccountService;
import com.spring.test.SpringSecurity_JWT_test.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class AccountController {


    private final AccountService accountService;
    private final BalanceService balanceService;

    public AccountController(AccountService accountService, BalanceService balanceService) {
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    @GetMapping("/")
    public List<Account> getAllAccountsByUserId(@RequestParam Integer id){
        return accountService.getAccountsByUserId(id);
    }

    @PostMapping("/account")
    public Account addAccount(@RequestBody Account account){
        Account account1 = accountService.saveAccount(account);
        AccountSerializer serializer = new AccountSerializer();
        serializer.deserializeObject(account1);
        System.out.println(account.toString());
        return serializer.deserializeObject(account1);
    }

    @PostMapping("/savings")
    public void addToSavings(@RequestParam Double savings, @RequestParam Integer id){
        accountService.updateSavingsAccount(savings, id);
    }

    @PostMapping("/deposit/balance")
    public ResponseEntity<Object> addToBalance(@RequestBody Balance balance,
                                               @RequestParam Integer id){


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
