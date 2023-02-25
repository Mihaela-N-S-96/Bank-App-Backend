package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.LoanRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Optional;

@Transactional
@Service
public class LoanServiceImpl implements LoanService{

    private final Double RATE_PERCENT = 30.0;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    private HashMap<String,?>getCheckResponse(Loan loan, String message){

        HashMap response = new HashMap();
        response.put("message", message);
        response.put("loan", loan);

        return  response;
    }
    public Loan saveLoan(Loan loan, Integer id_account){
     Optional<Account> account = accountRepository.findById(id_account);

     loan.setAccount(account.get());//convert Optional to Object -> account.get()
accountRepository.addValueToBalance(loan.getLoan(), id_account);
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

    public ResponseEntity<?> approveRate(Integer account_id, Loan loan){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        int months = 12 * loan.getYears();
        Double current_rate = Double.valueOf(decimalFormat.format(loan.getLoan() / months));

        if(loanRepository.getNumberOfLoans(account_id) >= 3)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    getCheckResponse(loan, "You have exceeded the maximum limit of 3 loans"));

        if(takeLoan(loan.getSalary(),getSumOfRateByAccountId(account_id, current_rate))) {
            loan.setRate(current_rate);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    getCheckResponse(loan, "Your loan is approved"));
        }
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    getCheckResponse(loan, "Unfortunately, your salary does not fit this request"));

    }
}
