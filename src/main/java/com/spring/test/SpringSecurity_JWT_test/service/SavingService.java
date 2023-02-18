package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public interface SavingService {

    public ArrayList<Saving> getAllSavings(Integer id);
    public Saving addNewSaving(Integer id_account, Saving saving);
    //public Saving saveSaving(Saving saving, Integer id);
    public HashMap<String, Object> getWithdrawResponse(Saving saving, Integer id_account);
    public List<Saving> addValueToSavingByIdSaving(Integer id, Double value, Integer id_account);


}
