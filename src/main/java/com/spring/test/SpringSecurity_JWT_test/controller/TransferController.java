package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import com.spring.test.SpringSecurity_JWT_test.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public Transfer addTransfer(@RequestBody Transfer transfer, @RequestParam Integer id, @RequestParam String email){

        return  transferService.saveTransfer(transfer,id, email);
    }
}
