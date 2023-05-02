package com.spring.test.SpringSecurity_JWT_test.controller;


import com.spring.test.SpringSecurity_JWT_test.model.Balance;
import com.spring.test.SpringSecurity_JWT_test.service.BalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balance")
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE},
//        allowCredentials = "true", allowedHeaders = {"Content-Type", "Authorization", "X-XSRF-TOKEN","X-CSRF-TOKEN"})
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/")
    public List<Balance> addToBalance(@RequestParam Integer id){

        return balanceService.getAllBalanceByAccountId(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addToBalance(@RequestBody Balance balance,
                                               @RequestParam Integer id){


        return new ResponseEntity<>(balanceService.getBalanceResponse(balance, id),
                HttpStatus.OK);
    }


}
