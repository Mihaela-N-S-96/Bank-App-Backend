package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.*;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.HistoryLoanRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryLoanServiceImpl implements HistoryLoanService{

    @Autowired
    private HistoryLoanRepository historyLoanRepository;

    @Resource
    private LoanRepository loanRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountService;

    @Transactional
    public void saveLoan(HistoryLoan historyLoan, Integer id, Integer account_id){
        Optional<Loan> loan = loanRepository.findById(id);

        historyLoan.setLoan(loan.get());//convert Optional to Object -> account.get()
        historyLoan = historyLoanRepository.saveAndFlush(historyLoan);

//        return historyLoan;
    }

    @Transactional
    public List<LoanJoinHistory> getPayResponse(HistoryLoan historyLoan,
                                                       Integer id_loan, Integer account_id){

        Optional<Loan> loan = loanRepository.findById(id_loan);

        saveLoan(historyLoan, id_loan, account_id);

        if(loanRepository.getTotalPayByLoanId(id_loan) != null ) {

           Double sumOfPayments = loanRepository.getTotalPayByLoanId(id_loan) + loan.get().getRate();
           loanRepository.setTotalPaidById(id_loan, sumOfPayments);
           loan.get().setTotal_paid(sumOfPayments);
//           accountRepository.decreasesValueFromBalance(loan.get().getRate(), account_id);
            accountService.decreasesValueFromBalance(loan.get().getRate(), account_id);
        }

        List<LoanJoinHistory> responseObjectList = new ArrayList<>();
        responseObjectList = loanRepository.findAllJoinHistoryLoanByAccountId(account_id);

        return responseObjectList;
    }

    public HashMap<String, ArrayList> getAllHistoryResponse(Integer account_id){

        ArrayList<LoanJoinHistory> listAllPays = loanRepository.findAllJoinHistoryLoanByAccountId(account_id);
        ArrayList<Loan> listAllLoans = loanRepository.getAllLoansByAccountId(account_id);

        HashMap<String, ArrayList> response = new HashMap<>();
        response.put("listAllPays", listAllPays);
        response.put("listAllLoans", listAllLoans);

        return new HashMap<>(response);

    }
}
