package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Deposit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public interface DepositService {
    public HashMap<String, Object> getDepositResponse(Deposit deposit, Integer id_account);
    public Deposit saveDeposit(Deposit deposit, Integer id);
    public ArrayList<Deposit> getDepositList(Integer id_account);
}
