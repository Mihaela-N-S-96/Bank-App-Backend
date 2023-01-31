package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface LoanService  {
    public Loan saveLoan(Loan loan, Integer id);
    public boolean takeLoan(Double salary, Double previous_rates);
    public Double getSumOfRateByAccountId(Integer id, Double current_rate);
    public Loan approveRate(Integer account_id, Loan loan);
}
