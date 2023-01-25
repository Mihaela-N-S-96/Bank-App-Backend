package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import com.spring.test.SpringSecurity_JWT_test.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/loans")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/check")
    public void approveNewRate(@RequestBody Loan loan, @RequestParam Integer id){

        loanService.approveRate(id, loan);
    }

    @PostMapping("/loan")
    public Loan addLoan(@RequestBody Loan loan, @RequestParam Integer id){

        return loanService.saveLoan(loan, id);
    }


}
