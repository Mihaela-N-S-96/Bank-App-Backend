package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.TransferRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserDetailRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class TransferServiceImpl implements TransferService{
    private final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);

    private UserDetailRepository userDetailRepository;
    private AccountRepository accountRepository;

    private AccountService accountService;

    private UserRepository userRepository;

    private TransferRepository transferRepository;

    public TransferServiceImpl(UserDetailRepository userDetailRepository, AccountRepository accountRepository, AccountService accountService, UserRepository userRepository, TransferRepository transferRepository) {
        this.userDetailRepository = userDetailRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.userRepository = userRepository;
        this.transferRepository = transferRepository;
    }

    public ArrayList<Transfer> getAllAccountIdTransfers(Integer account_id){
        ArrayList<Transfer> response = transferRepository.getAllTransfersByAccountId(account_id);

        if(response.isEmpty()) throw  new RequestException("This list is empty");
            else  return response;
    }

    public Integer getUserIdByAccountId(Integer id_account){
        return accountRepository.getAccountById(id_account).getUser_id();
    }

    @Transactional
    public void addTransferToBalance(Double newValue, Integer id_user, String currency){

        Account account = accountRepository.findByUserIdAndCurrency(id_user, currency);
        account.setBalance(account.getBalance() + newValue);

        accountRepository.save(account);
    }



    @Transactional
    public Transfer saveTransfer(Transfer transfer, Integer id_account, String email)  {
        Account fromAccount;
        Integer id_user;
        Integer from_user_id = getUserIdByAccountId(id_account);

        //Find the sender account by its id
         if(!accountRepository.findById(id_account).isPresent()){
             log.error("Can not find sender's account with this id!");
             throw new RequestException("Can not find account with this id!");

         } else {
             fromAccount = accountRepository.getAccountById(id_account);
         }

         //Find the receiver account by email
        if(userRepository.findByEmail(email) == null){
            log.error("Can not find user with this email!");
            throw new RequestException("Can not find user with this email!");
        }else {
            id_user = userRepository.findByEmail(email).getId();
            if(accountRepository.findByUserIdAndCurrency(id_user, fromAccount.getCurrency()) == null){
                log.error("Can not find receiver's account with this id and this currency type!");
                throw new RequestException("Can not find receiver's account with this id and this currency type!");
            }else{
                //If sender and receiver was identified, set their details in transfer table and make the transfer
                //from sender's balance account to receiver's balance account
                if(accountService.hasMoneyInAccount(transfer.getTransfer(), id_account) == true) {
                    transfer.setAccount(fromAccount);
                    transfer.setTo_account_id(accountRepository.findByUserIdAndCurrency(id_user, fromAccount.getCurrency()).getId());//receiver_id
                    transfer.setTo_receiver_name(userDetailRepository.findByUserEmail(email).getFirst_name());
                    transfer.setFrom_sender_name(userDetailRepository.findByUserId(from_user_id).getFirst_name());

                    accountService.decreasesValueFromBalance(transfer.getTransfer(), id_account);
                    addTransferToBalance(transfer.getTransfer(), id_user, fromAccount.getCurrency());

                    transfer = transferRepository.save(transfer);
                } else throw  new RequestException("You don't have enough money in account!");
            }

        }

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


