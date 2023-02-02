package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.LoanRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService{

    private final Double RATE_PERCENT = 30.0;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public Loan saveLoan(Loan loan, Integer id){
     Optional<Account> account = accountRepository.findById(id);

     loan.setAccount(account.get());//convert Optional to Object -> account.get()

//        if(loanRepository.getSumOfPayByAccountId(id) == null )
            if(loan.getTotal_paid() == null)
            loan.setTotal_paid(0.0);

//        else
//     loan.setTotal_paid(loanRepository.getSumOfPayByAccountId(id)+ loan.getRate());
     loan = loanRepository.save(loan);
        return loan;
    }

    public boolean takeLoan(Double salary, Double previous_rates){
        Double approveRate = ( RATE_PERCENT / 100 )* salary;

        if(previous_rates <= approveRate)
            return true;
                 else return false;
    }

    public Double getSumOfRateByAccountId(Integer id, Double current_rate){
        if(loanRepository.getSumOfRates(id) == null)
            return 0.00;
        else
        return current_rate + loanRepository.getSumOfRates(id);
    }

    public Loan approveRate(Integer account_id, Loan loan){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        int months = 12 * loan.getYears();
        Double current_rate = Double.valueOf(decimalFormat.format(loan.getLoan() / months));

        if(takeLoan(loan.getSalary(),getSumOfRateByAccountId(account_id, current_rate))) {
            loan.setRate(current_rate);
            //throw new RequestException("Success");
            return loan;
        }
        else throw new RequestException("Failed");
    }
}
