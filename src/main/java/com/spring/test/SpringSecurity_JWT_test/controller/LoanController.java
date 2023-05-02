package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.HistoryLoan;
import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import com.spring.test.SpringSecurity_JWT_test.model.LoanJoinHistory;
import com.spring.test.SpringSecurity_JWT_test.service.HistoryLoanService;
import com.spring.test.SpringSecurity_JWT_test.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.spring.test.SpringSecurity_JWT_test.controller.AuthController.CSRF_TOKEN_HEADER_NAME;


@RestController
@RequestMapping("/loans")
public class LoanController extends CsrfController{


    private final LoanService loanService;
    private final HistoryLoanService historyLoanService;


    public LoanController(LoanService loanService, HistoryLoanService historyLoanService) {
        this.loanService = loanService;
        this.historyLoanService = historyLoanService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllLoans(@RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                              HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return  new ResponseEntity<>(historyLoanService.getAllHistoryResponse(id), HttpStatus.OK);
    }

    @PostMapping("/check")
    public Loan approveNewRate(@RequestBody Loan loan, @RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                               HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return loanService.approveRate(id, loan);
     }

    @PostMapping("/loan")
    public Loan addLoan(@RequestBody Loan loan, @RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                        HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return loanService.saveLoan(loan, id);
    }

    @PostMapping("/pay")
    public List<LoanJoinHistory> addPayLoan(@RequestBody HistoryLoan historyLoan,
                                            @RequestParam Integer id,
                                            @RequestParam Integer account_id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                            HttpSession session) throws Exception{//loan_id

        validateCsrfToken(csrfToken, session);
        return historyLoanService.getPayResponse(historyLoan, id, account_id);
    }


}
