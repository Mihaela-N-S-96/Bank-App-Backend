package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public boolean validateBalance(Account account, Double value){
        if(value<= account.getBalance())
            return true;
        else return false;
    }

    public boolean savingExist(Saving saving){
        List<Saving> savingList = savingRepository.findByDetails(saving.getDetails());
        if(savingList.isEmpty()){
            return false;
        }
        return true;
    }

//    public Saving addValueToSavingBySavingId(Integer id, Double value){
//        try {
//            Saving saving = savingRepository.findOneById(id);
//        }catch (Exception e){
//            throw new RuntimeException("This service dose not exists!");
//        }
//
//    }

    public boolean pocketIsEmpty(Integer id_saving){
        if(savingRepository.findOneById(id_saving).getTransfer() == 0)
            return true;
        return false;
    }

    public ArrayList<Saving> getAllSavings(Integer id){
        return savingRepository.getAllSavingsByAccountId(id);
    }

    @Transactional
    public Saving addNewSaving(Integer id_account, Saving saving){

        if(!savingExist(saving) && validateBalance(accountRepository.findById(id_account).get(), saving.getTransfer())){
            accountRepository.decreasesValueFromBalance(saving.getTransfer(), id_account);
            accountRepository.addValueToSavings(saving.getTransfer(), id_account);

            saving.setAccount(accountRepository.findById(id_account).get());
            saving = savingRepository.save(saving);
            return saving;
        }
        else if(savingExist(saving)) throw new RequestException("This saving already exists!");
        else throw new RequestException("You don't have enough money in current balance!");

    }

    @Transactional
    public Saving withdrawSaving(Saving saving, Integer id){
        Optional<Account> account = accountRepository.findById(id);
        saving.setAccount(account.get());

        if(validateSaving(account.get(), saving.getTransfer())) {
            accountRepository.decreasesValueFromSavings(saving.getTransfer(), id);
            savingRepository.decreasesValueFromSavings(saving.getTransfer(), saving.getId());
            try {
                if (pocketIsEmpty(saving.getId()))
                    savingRepository.delete(saving);
            }catch (Exception e){
                throw new RequestException("This saving dose not exists!");
            }
        }
            else throw new RequestException("You don't have enough money!");

        return new Saving(saving.getId(), saving.getTransfer(), saving.getStatus(), saving.getDate(), saving.getDetails());
    }

    @Transactional
    public HashMap<String, Object> getWithdrawResponse(Saving saving,
                                                      Integer id_account){


        Saving savingObject = withdrawSaving(saving, id_account);

        HashMap<String, Object> savingResponse = new HashMap<>();
        savingResponse.put("deposit", savingObject);
        savingResponse.put("status", savingObject.getStatus());

        return new HashMap<>(savingResponse);

    }

    @Transactional
    public List<Saving> addValueToSavingByIdSaving(Integer id, Double value, Integer id_account){
        savingRepository.addValueToSavingByIdSaving(id, value);
        accountRepository.addValueToSavings(value,id_account);
        accountRepository.decreasesValueFromBalance(value,id_account);

       return savingRepository.getAllSavingsByAccountId(id_account);
    }


}
