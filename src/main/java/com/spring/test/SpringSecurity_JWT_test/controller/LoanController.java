package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.HistoryLoan;
import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import com.spring.test.SpringSecurity_JWT_test.model.LoanJoinHistory;
import com.spring.test.SpringSecurity_JWT_test.service.HistoryLoanService;
import com.spring.test.SpringSecurity_JWT_test.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/loans")
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH},
//        allowCredentials = "true", allowedHeaders = {"Content-Type", "Authorization", "X-XSRF-TOKEN","X-CSRF-TOKEN"})
public class LoanController {


    private final LoanService loanService;
    private final HistoryLoanService historyLoanService;


    public LoanController(LoanService loanService, HistoryLoanService historyLoanService) {
        this.loanService = loanService;
        this.historyLoanService = historyLoanService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllLoans(@RequestParam Integer id){

        return  new ResponseEntity<>(historyLoanService.getAllHistoryResponse(id), HttpStatus.OK);
    }

    @PostMapping("/check")
    public Loan approveNewRate(@RequestBody Loan loan, @RequestParam Integer id){

        return loanService.approveRate(id, loan);
     }

    @PostMapping("/loan")
    public Loan addLoan(@RequestBody Loan loan, @RequestParam Integer id){

        return loanService.saveLoan(loan, id);
    }

    @PostMapping("/pay")
    public List<LoanJoinHistory> addPayLoan(@RequestBody HistoryLoan historyLoan,
                                            @RequestParam Integer id,
                                            @RequestParam Integer account_id){//loan_id

        return historyLoanService.getPayResponse(historyLoan, id, account_id);
    }


}
