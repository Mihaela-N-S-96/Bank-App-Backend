package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Withdrawal;
import org.springframework.stereotype.Component;

@Component
public interface WithdrawalService {

    public Withdrawal saveWithdrawal(Withdrawal withdrawal, Integer id);
}
