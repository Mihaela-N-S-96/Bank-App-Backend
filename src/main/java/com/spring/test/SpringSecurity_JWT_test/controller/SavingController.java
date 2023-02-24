package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import com.spring.test.SpringSecurity_JWT_test.service.SavingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/savings")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class SavingController {

    private SavingService savingService;

    public SavingController(SavingService savingService) {
        this.savingService = savingService;
    }


    @GetMapping("/")
    public ArrayList<Saving> getAllSavings(@RequestParam Integer id){

        return  savingService.getAllSavings(id);
    }
    @PostMapping("/new")
    public ResponseEntity<Object> addNewSaving(@RequestBody Saving saving,
                                               @RequestParam Integer id){

        return new ResponseEntity<>(savingService.addNewSaving(id,saving), HttpStatus.OK);
    }
    @PostMapping("/withdraw")
    public ResponseEntity<Object> withdrawSaving(@RequestBody Saving saving,
                                                 @RequestParam Integer id_account){

        return new ResponseEntity<>(savingService.getWithdrawResponse(saving,id_account), HttpStatus.OK);
    }

    @PatchMapping("/add")
    public List<Saving> increaseSaving(@RequestParam Integer id,
                                       @RequestParam Double value,
                                       @RequestParam Integer id_account){

        return savingService.addValueToSavingByIdSaving(id, value, id_account);
    }
}
