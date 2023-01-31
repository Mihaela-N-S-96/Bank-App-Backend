package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.*;
import com.spring.test.SpringSecurity_JWT_test.repository.HistoryLoanRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryLoanServiceImpl implements HistoryLoanService{

    @Autowired
    private HistoryLoanRepository historyLoanRepository;

    @Resource
    private LoanRepository loanRepository;


    @Transactional
    public void saveLoan(HistoryLoan historyLoan, Integer id, Integer account_id){
        Optional<Loan> loan = loanRepository.findById(id);

        historyLoan.setLoan(loan.get());//convert Optional to Object -> account.get()
        historyLoan = historyLoanRepository.saveAndFlush(historyLoan);

//        return historyLoan;
    }

    @Transactional
    public List<LoanJoinHistory> getLoanHistoryResponse(HistoryLoan historyLoan,
                                                       Integer id_loan, Integer account_id){
        Optional<Loan> loan = loanRepository.findById(id_loan);

        saveLoan(historyLoan, id_loan, account_id);

        if(loanRepository.getSumOfPayByAccountId(account_id) != null ) {
           Double sumOfPayments = loanRepository.getSumOfPayByAccountId(account_id) + loan.get().getRate();
           loanRepository.setTotalPaidById(id_loan, sumOfPayments);
           loan.get().setTotal_paid(sumOfPayments);
        }

        List<LoanJoinHistory> responseObjectList = new ArrayList<>();
        responseObjectList = loanRepository.findAllJoinHistoryLoan();

        return responseObjectList;
    }
}
