//package com.spring.test.SpringSecurity_JWT_test.service;
//
//import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
//import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
//import com.spring.test.SpringSecurity_JWT_test.repository.TransferRepository;
//import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class TransferServiceImplTest {
//
//     @Autowired
//     AccountRepository accountRepository;
//     AccountService accountService;
//     UserRepository userRepository;
//     TransferRepository transferRepository;
//
//     TransferServiceImpl transferService;
//
////    @Test
////    @Rollback(value = false)
////    @DisplayName("Add transfer value to balance from account")
////    void addTransferToBalance() {
////        TransferServiceImpl transfer = new TransferServiceImpl(accountRepository,  accountService,  userRepository,  transferRepository);
////
////        Double oldValue = accountRepository.getAccountById(462).getBalance();
////        transfer.addTransferToBalance(5.0, 119, "ron_Test");
////
////        assertThat(accountRepository.getAccountById(462).getBalance()).isEqualTo(oldValue + 5.0);
////
////    }
//}