package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


import static com.spring.test.SpringSecurity_JWT_test.controller.AuthController.CSRF_TOKEN_HEADER_NAME;


@RestController
@RequestMapping("/accounts")
public class AccountController extends CsrfController {


    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public List<Account> getAllAccountsByUserId(@RequestParam Integer id,@RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                                HttpSession session) throws Exception {

        validateCsrfToken(csrfToken, session);
        return accountService.getAccountsByUserId(id);
    }

    @PostMapping("/account")
    public Account addAccount(@RequestBody Account account, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                              HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return accountService.saveAccount(account);
    }

    @PostMapping("/savings")
    public void addToSavings(@RequestParam Double savings, @RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                             HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        accountService.increaseSavingsAccount(savings, id);
    }


    @PatchMapping("/edit")
    public ResponseEntity<Object> editAccount(@RequestParam Integer id, @RequestParam String type, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                              HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return new ResponseEntity<>(accountService.editTypeOfPlanByAccountId(id, type), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                           HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        ResponseEntity response;
        try{
            response = accountService.deleteByAccountId(id);
        }catch (Exception e){
            throw new RequestException(e.getMessage());
        }

        return response;
    }

}
