package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import com.spring.test.SpringSecurity_JWT_test.service.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/savings")
public class SavingController {

    @Autowired
    private SavingService savingService;

    @PostMapping("/new")
    public ResponseEntity<Object> addSaving(@RequestBody Saving saving,
                                            @RequestParam Integer id){
System.out.println(saving);
        return new ResponseEntity<>(savingService.getSavingResponse(saving,id), HttpStatus.OK);
    }
//    @PostMapping("/withdraw")
//    public ResponseEntity<Object> decreasesSaving(@RequestBody Saving saving,
//                                            @RequestParam Integer id_account){
//
//        return new ResponseEntity<>(savingService.getSavingResponse(saving,id_account), HttpStatus.OK);
//    }
}
