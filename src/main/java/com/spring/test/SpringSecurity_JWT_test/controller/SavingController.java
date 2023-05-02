package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import com.spring.test.SpringSecurity_JWT_test.service.SavingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.spring.test.SpringSecurity_JWT_test.controller.AuthController.CSRF_TOKEN_HEADER_NAME;

@RestController
@RequestMapping("/savings")
public class SavingController extends CsrfController {

    private SavingService savingService;

    public SavingController(SavingService savingService) {
        this.savingService = savingService;
    }


    @GetMapping("/")
    public ArrayList<Saving> getAllSavings(@RequestParam Integer id,
                                           @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                           HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return  savingService.getAllSavings(id);
    }
    @PostMapping("/new")
    public ResponseEntity<Object> addNewSaving(@RequestBody Saving saving,
                                               @RequestParam Integer id,
                                               @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                               HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return new ResponseEntity<>(savingService.addNewSaving(id,saving), HttpStatus.OK);
    }
    @PostMapping("/withdraw")
    public ResponseEntity<Object> withdrawSaving(@RequestBody Saving saving,
                                                 @RequestParam Integer id_account,
                                                 @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                                 HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return new ResponseEntity<>(savingService.getWithdrawResponse(saving,id_account), HttpStatus.OK);
    }

    @PatchMapping("/add")
    public List<Saving> increaseSaving(@RequestParam Integer id,
                                       @RequestParam Double value,
                                       @RequestParam Integer id_account,
                                       @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                       HttpSession session) throws Exception{

        validateCsrfToken(csrfToken, session);
        return savingService.addValueToSavingByIdSaving(id, value, id_account);
    }
}
