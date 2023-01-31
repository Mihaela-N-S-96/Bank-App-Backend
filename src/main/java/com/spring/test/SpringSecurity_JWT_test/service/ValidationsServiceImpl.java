package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.repository.DepositRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidationsServiceImpl {

    private final DepositRepository depositRepository;

    public ValidationsServiceImpl (DepositRepository depositRepository){
        this.depositRepository = depositRepository;
    }

    public void verifyIfIdExist(Integer id){
        if(!depositRepository.existsById(id)){

            throw new RequestException("This id dose not exist!");
        }
    }
}
