package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Withdrawal;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/withdrawals")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;


    @PatchMapping("/withdrawal")
    public Withdrawal addWithdrawal(@RequestBody Withdrawal withdrawal, @RequestParam Long id){

        withdrawal = withdrawalService.saveWithdrawal(withdrawal, id);


        return  withdrawal;

    }
}
