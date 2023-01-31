package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.HistoryLoan;
import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import com.spring.test.SpringSecurity_JWT_test.model.LoanJoinHistory;
import com.spring.test.SpringSecurity_JWT_test.repository.LoanRepository;
import com.spring.test.SpringSecurity_JWT_test.service.HistoryLoanService;
import com.spring.test.SpringSecurity_JWT_test.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/loans")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private HistoryLoanService historyLoanService;
    @GetMapping("/")
    public ArrayList<Loan> getAllLoans(@RequestParam Integer id){

        return  loanRepository.getAllLoansByAccountId(id);
    }

    @GetMapping("/check")
    public void approveNewRate(@RequestBody Loan loan, @RequestParam Integer id){

        loanService.approveRate(id, loan);
    }

    @PostMapping("/loan")
    public Loan addLoan(@RequestBody Loan loan, @RequestParam Integer id){

        return loanService.saveLoan(loan, id);
    }

    @PostMapping("/pay")
    public List<LoanJoinHistory> addPayLoan(@RequestBody HistoryLoan historyLoan, @RequestParam Integer id, @RequestParam Integer account_id){//loan_id

        return historyLoanService.getLoanHistoryResponse(historyLoan, id, account_id);
    }


}
