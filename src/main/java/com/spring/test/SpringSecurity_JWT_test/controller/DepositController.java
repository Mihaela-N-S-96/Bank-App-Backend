package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Deposit;
import com.spring.test.SpringSecurity_JWT_test.repository.DepositRepository;
import com.spring.test.SpringSecurity_JWT_test.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static com.spring.test.SpringSecurity_JWT_test.controller.AuthController.CSRF_TOKEN_HEADER_NAME;

@RestController
@RequestMapping("/deposit")
public class DepositController extends CsrfController{

    private final DepositService depositService;


    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }


    @GetMapping("/")
    public ArrayList<Deposit> getAllDeposits(@RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                             HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return  depositService.getDepositList(id);
    }

    @PostMapping("/new")
    public ResponseEntity<Object> addDeposit(@RequestBody Deposit deposit,
                                             @RequestParam Integer id, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                             HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return new ResponseEntity<>(depositService.getDepositResponse(deposit, id), HttpStatus.OK);
    }

//    @PostMapping("/withdraw")
//    public ResponseEntity<Object> decreasesDeposit(@RequestBody Deposit deposit,
//                                                   @RequestParam Integer id){
//
//        return new ResponseEntity<>(depositService.getDepositResponse(deposit, id), HttpStatus.OK);
//    }


}
