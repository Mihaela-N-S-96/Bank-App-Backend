package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Deposit;
import com.spring.test.SpringSecurity_JWT_test.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @PostMapping("/new")
    public ResponseEntity<Object> addDeposit(@RequestBody Deposit deposit,
                                             @RequestParam Integer id){

        return new ResponseEntity<>(depositService.getDepositResponse(deposit, id), HttpStatus.OK);
    }

//    @PostMapping("/withdraw")
//    public ResponseEntity<Object> decreasesDeposit(@RequestBody Deposit deposit,
//                                                   @RequestParam Integer id){
//
//        return new ResponseEntity<>(depositService.getDepositResponse(deposit, id), HttpStatus.OK);
//    }


}
