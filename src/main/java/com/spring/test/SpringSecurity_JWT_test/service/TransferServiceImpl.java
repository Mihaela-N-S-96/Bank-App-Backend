package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.TransferRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransferServiceImpl implements TransferService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Transactional
    public Transfer saveTransfer(Transfer transfer, Integer id_account, String email){

        accountRepository.decreasesValueFromBalance(transfer.getTransfer(), id_account);

        Account account = accountService.findById(id_account).get();
        Integer id_user = userRepository.findByEmail(email).getId();
        accountRepository.addTransferToBalance(transfer.getTransfer(), id_user, account.getCurrency());

        transfer.setAccount(account);
        transfer = transferRepository.save(transfer);
        return transfer;

    }
}
