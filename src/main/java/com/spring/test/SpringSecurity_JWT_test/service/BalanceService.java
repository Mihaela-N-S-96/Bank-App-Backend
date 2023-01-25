package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Balance;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.HashMap;

@Component
public interface BalanceService {
    public HashMap<String, Object> getBalanceResponse(Balance balance,
                                                      Integer id_account);
}
