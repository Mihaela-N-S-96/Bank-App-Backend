package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface LoanService  {
    public Loan saveLoan(Loan loan, Integer id);
}
