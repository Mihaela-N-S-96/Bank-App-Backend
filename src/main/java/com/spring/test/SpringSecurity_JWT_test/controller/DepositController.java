package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Deposit;
import com.spring.test.SpringSecurity_JWT_test.repository.DepositRepository;
import com.spring.test.SpringSecurity_JWT_test.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/deposit")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class DepositController {

    private final DepositService depositService;


    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }


    @GetMapping("/")
    public ArrayList<Deposit> getAllDeposits(@RequestParam Integer id){

        return  depositService.getDepositList(id);
    }

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
