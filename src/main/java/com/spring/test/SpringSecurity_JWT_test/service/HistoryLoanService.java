package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.HistoryLoan;
import com.spring.test.SpringSecurity_JWT_test.model.LoanJoinHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface HistoryLoanService {
    public void saveLoan(HistoryLoan historyLoan, Integer id, Integer account_id);
    public List<LoanJoinHistory> getPayResponse(HistoryLoan historyLoan,
                                                        Integer id_loan, Integer account_id);
    public HashMap<String, ArrayList> getAllHistoryResponse(Integer account_id);
}
