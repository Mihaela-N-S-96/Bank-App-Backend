package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import com.spring.test.SpringSecurity_JWT_test.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transfers")
public class TransferController {


    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<Object> addTransfer(@RequestBody Transfer transfer,
                                              @RequestParam Integer id,
                                              @RequestParam String email){

        return  new ResponseEntity<>(transferService.getTransferResponse(
                                    transfer, id, email
        ), HttpStatus.OK);
    }
}
