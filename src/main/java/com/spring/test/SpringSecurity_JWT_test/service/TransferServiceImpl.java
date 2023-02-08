package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.TransferRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;


@Service
public class TransferServiceImpl implements TransferService{
    private final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransferRepository transferRepository;

    // that methods save the second transfer (for receiver's history)
    @Transactional
    public void saveToTransferObject(Integer id_user, Account fromAccount, Transfer transfer ){

        Transfer transferTo = new Transfer();
        Account toAccount = new Account();
        toAccount = accountRepository.findOneByUserId(id_user, fromAccount.getCurrency());
        transferTo.setAccount(toAccount);
        transferTo.setDate(transfer.getDate());
        transferTo.setDetails(transfer.getDetails());
        transferTo.setTransfer(transfer.getTransfer());
        transferRepository.save(transferTo);
    }

    @Transactional
    public Transfer saveTransfer(Transfer transfer, Integer id_account, String email) {
        Account fromAccount = new Account();
        Integer id_user;

         accountRepository.decreasesValueFromBalance(transfer.getTransfer(), id_account);

         if(!accountRepository.findById(id_account).isPresent()){
             throw new RequestException("Can not find account with this id!");
         } else {
             fromAccount = accountService.findById(id_account).get();
         }

        if(userRepository.findByEmail(email) == null){
            throw new RequestException("Can not find user with this email!");
        }else {
            id_user = userRepository.findByEmail(email).getId();
        }

        accountRepository.addTransferToBalance(transfer.getTransfer(), id_user, fromAccount.getCurrency());

        transfer.setAccount(fromAccount);
        transfer = transferRepository.save(transfer);

        saveToTransferObject( id_user, fromAccount, transfer );


        return new Transfer(transfer.getId(), transfer.getTransfer(), transfer.getDate(), transfer.getDetails());

    }

    @Transactional
    public HashMap<String, Object> getTransferResponse(Transfer transfer,
                                                       Integer id,
                                                       String email){
        Transfer transferResponse = saveTransfer(transfer,id, email);

        String receiverName = userRepository.findByEmail(email).getUsername();
        String senderName = userRepository.findByAccountId(id).getUsername();

        HashMap<String, Object> responseTransfer = new HashMap<>();
        responseTransfer.put("transfer", transferResponse);
        responseTransfer.put("receiver",receiverName);
        responseTransfer.put("sender",senderName);

        return responseTransfer;
    }
}
