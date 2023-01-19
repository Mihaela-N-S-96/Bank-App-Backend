package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Optional;

@Service
public class SavingServiceImpl implements SavingService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SavingRepository savingRepository;


    public boolean validateSaving(Account account, Double value){
        if(value<= account.getSavings())
            return true;
        else return false;
    }

    @Transactional
    public Saving saveSaving(Saving saving, Integer id){
        Optional<Account> account = accountRepository.findById(id);

        saving.setAccount(account.get());

        saving = savingRepository.save(saving);
        if(saving.getStatus().contains("deposit"))
            accountRepository.addValueToSavings(saving.getTransfer(), id);
        else
        if(saving.getStatus().contains("withdraw") && validateSaving(account.get(), saving.getTransfer()))
            accountRepository.decreasesValueFromSavings(saving.getTransfer(), id);
        else throw new RequestException("Forbidden");
        return new Saving(saving.getId(), saving.getTransfer(), saving.getStatus(), saving.getDate(), saving.getDetails());
    }

    @Transactional
    public HashMap<String, Object> getSavingResponse(Saving saving,
                                                      Integer id_account){


        Saving savingObject = saveSaving(saving, id_account);

        HashMap<String, Object> savingResponse = new HashMap<>();
        savingResponse.put("deposit", savingObject);
        savingResponse.put("status", savingObject.getStatus());

        return new HashMap<>(savingResponse);

    }
}
