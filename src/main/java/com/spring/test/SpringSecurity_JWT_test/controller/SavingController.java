package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import com.spring.test.SpringSecurity_JWT_test.repository.SavingRepository;
import com.spring.test.SpringSecurity_JWT_test.service.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/savings")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class SavingController {

    @Autowired
    private SavingService savingService;

    @Autowired
    private SavingRepository savingRepository;

    @GetMapping("/")
    public ArrayList<Saving> geatAllSavings(@RequestParam Integer id){

        return  savingRepository.getAllSavingsByAccountId(id);
    }
    @PostMapping("/new")
    public ResponseEntity<Object> addSaving(@RequestBody Saving saving,
                                            @RequestParam Integer id){
System.out.println(saving);
        return new ResponseEntity<>(savingService.getSavingResponse(saving,id), HttpStatus.OK);
    }
    @PostMapping("/withdraw")
    public ResponseEntity<Object> decreasesSaving(@RequestBody Saving saving,
                                            @RequestParam Integer id_account){

        return new ResponseEntity<>(savingService.getSavingResponse(saving,id_account), HttpStatus.OK);
    }

    @PatchMapping("/add")
    public List<Saving> increaseSaving(@RequestParam Integer id,
                                       @RequestParam Double value,
                                       @RequestParam Integer id_account){

        return savingService.addValueToSavingByIdSaving(id, value, id_account);
    }
}
