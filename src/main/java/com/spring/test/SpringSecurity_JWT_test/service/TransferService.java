package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import org.springframework.stereotype.Component;

@Component
public interface TransferService {

    public Transfer saveTransfer(Transfer transfer, Integer id_account, String email);
}