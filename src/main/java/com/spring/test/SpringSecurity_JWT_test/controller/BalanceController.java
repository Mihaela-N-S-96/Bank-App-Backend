package com.spring.test.SpringSecurity_JWT_test.controller;


import com.spring.test.SpringSecurity_JWT_test.model.Balance;
import com.spring.test.SpringSecurity_JWT_test.service.BalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.spring.test.SpringSecurity_JWT_test.controller.AuthController.CSRF_TOKEN_HEADER_NAME;

@RestController
@RequestMapping("/balance")
public class BalanceController extends CsrfController{

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/")
    public List<Balance> addToBalance(@RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                      HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return balanceService.getAllBalanceByAccountId(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addToBalance(@RequestBody Balance balance,
                                               @RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                               HttpSession session) throws Exception{


        validateCsrfToken(csrfToken, session);
        return new ResponseEntity<>(balanceService.getBalanceResponse(balance, id),
                HttpStatus.OK);
    }


}
