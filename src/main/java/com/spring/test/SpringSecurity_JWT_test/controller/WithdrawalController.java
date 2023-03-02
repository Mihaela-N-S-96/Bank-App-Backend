package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Withdrawal;
import com.spring.test.SpringSecurity_JWT_test.service.WithdrawalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/withdrawals")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class WithdrawalController {

    private WithdrawalService withdrawalService;

    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @PatchMapping("/withdrawal")
    public Withdrawal addWithdrawal(@RequestBody Withdrawal withdrawal, @RequestParam Integer id){

        withdrawal = withdrawalService.saveWithdrawal(withdrawal, id);


        return  withdrawal;

    }
}
