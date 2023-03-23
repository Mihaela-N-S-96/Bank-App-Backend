package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.SavingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SavingServiceImpl implements SavingService{

    private final AccountRepository accountRepository;
    private final SavingRepository savingRepository;
    private final AccountServiceImpl accountService;


    public SavingServiceImpl(AccountRepository accountRepository, SavingRepository savingRepository, AccountServiceImpl accountService) {
        this.accountRepository = accountRepository;
        this.savingRepository = savingRepository;
        this.accountService = accountService;

    }


    public boolean validateValue(Double dataBaseValue, Double newValue){
        if(newValue <= dataBaseValue)
            return true;
        else return false;
    }

    public boolean savingExist(Saving saving, Integer account_id){
        List<Saving> savingList = savingRepository.findByDetailsAndAccountId(saving.getDetails(),account_id);
        if(savingList.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean pocketIsEmpty(Integer id_saving){
        if(savingRepository.findOneById(id_saving).getTransfer() == 0)
            return true;
        return false;
    }

    @Transactional
    public void addValueToSavingBySavingId(Integer id, Double value){
            Saving saving = savingRepository.findOneById(id);
            saving.setTransfer(saving.getTransfer() + value);
            savingRepository.save(saving);

    }
    @Transactional
    public void decreasesValueFromSavingsEntity(Double value, Integer id_saving){
        try {
            Saving saving = savingRepository.findOneById(id_saving);
            saving.setTransfer(saving.getTransfer() - value);

            savingRepository.save(saving);
        }catch (Exception e){
            throw new RequestException("This saving can not be decreased!");
        }
    }
    public ArrayList<Saving> getAllSavings(Integer id){
        if(savingRepository.getAllSavingsByAccountId(id).isEmpty())
            throw new RequestException("You don't have savings yet!");

        return savingRepository.getAllSavingsByAccountId(id);
    }


    @Transactional
    public Saving addNewSaving(Integer id_account, Saving saving){

        if(!savingExist(saving,id_account) && validateValue(accountRepository.findById(id_account).get().getBalance(), saving.getTransfer())){
            accountService.decreasesValueFromBalance(saving.getTransfer(), id_account);
            accountService.addValueToSavings(saving.getTransfer(), id_account);

            saving.setAccount(accountRepository.findById(id_account).get());
            saving = savingRepository.save(saving);
            saving.setAccount_id(saving.getAccount().getId()); // set the account_id field

            return saving;
        }
        else if(savingExist(saving,id_account)) throw new RequestException("This saving already exists!");
        else throw new RequestException("You don't have enough money in current balance!");
    }


    @Transactional
    public Saving withdrawSaving(Saving saving, Integer id){
        Optional<Account> account = accountRepository.findById(id);
        saving.setAccount(account.get());

        if(validateValue(account.get().getSavings(), saving.getTransfer())) {
             accountService.decreasesValueFromSavings(saving.getTransfer(), id);
             decreasesValueFromSavingsEntity(saving.getTransfer(), saving.getId());
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

        if(accountService.hasMoneyInAccount(value, id_account)==true){
            addValueToSavingBySavingId(id, value);
            accountService.addValueToSavings(value,id_account);
            accountService.decreasesValueFromBalance(value, id_account);
        } else throw new RequestException("You don't have enough money in account!");

       return savingRepository.getAllSavingsByAccountId(id_account);
    }


}
