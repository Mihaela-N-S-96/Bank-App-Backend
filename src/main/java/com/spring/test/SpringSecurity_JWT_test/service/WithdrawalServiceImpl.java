package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Withdrawal;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class WithdrawalServiceImpl implements WithdrawalService{

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountService;

    @Transactional
    public Withdrawal saveWithdrawal(Withdrawal withdrawal, Integer id){
//        accountRepository.decreasesValueFromBalance(withdrawal.getWithdrawal(), id);
        accountService.decreasesValueFromBalance(withdrawal.getWithdrawal(), id);

        Optional<Account> account = accountRepository.findById(id);
        withdrawal.setAccount(account.get());

        withdrawal = withdrawalRepository.saveAndFlush(withdrawal);

        return withdrawal;
    }
}
