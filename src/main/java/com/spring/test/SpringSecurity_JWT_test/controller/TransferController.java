package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import com.spring.test.SpringSecurity_JWT_test.repository.TransferRepository;
import com.spring.test.SpringSecurity_JWT_test.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/transfers")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class TransferController {


    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/")
    public ArrayList<Transfer> getAllTransfers(@RequestParam Integer id){

        return  transferService.getAllAccountIdTransfers(id);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> addTransfer(@RequestBody Transfer transfer,
                                              @RequestParam Integer id,
                                              @RequestParam String email){

        return  new ResponseEntity<>(transferService.getTransferResponse(
                                    transfer, id, email
        ), HttpStatus.OK);
    }
}
