package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.HistoryLoan;
import com.spring.test.SpringSecurity_JWT_test.model.LoanJoinHistory;

import java.util.HashMap;
import java.util.List;

public interface HistoryLoanService {
    public HistoryLoan saveLoan(HistoryLoan historyLoan, Integer id, Integer account_id);
    public List<LoanJoinHistory> getLoanHistoryResponse(HistoryLoan historyLoan,
                                                        Integer id_loan, Integer account_id);
}
