package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public interface SavingService {

    public Saving saveSaving(Saving saving, Integer id);
    public HashMap<String, Object> getSavingResponse(Saving saving, Integer id_account);
}
