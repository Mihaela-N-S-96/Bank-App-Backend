//package com.spring.test.SpringSecurity_JWT_test.service;
//
//import com.spring.test.SpringSecurity_JWT_test.model.Account;
//import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Propagation;
//
//import javax.swing.text.html.Option;
//import javax.transaction.Transactional;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class DepositServiceImplTest_increaseDeposit_AccountEntity {
//
//    @Mock
//    public AccountRepository accountRepository;
//
//    @Mock
//    public DepositServiceImpl depositService;
//
//    @Test
//    @DisplayName("Test the increase deposit from account")
//    void increaseDeposit_AccountEntity() {
//        Integer accountId = 472;
//        Double value = 100.0;
//        Account account = new Account();
//        account.setId(accountId);
//        account.setDeposit(50.0);
//
//        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
//
//        // Act
//        depositService.increaseDeposit_AccountEntity(value, accountId);
//
//        // Assert
//        assertEquals(150.0, account.getDeposit());
//        //assertEquals(account, accountRepository.findById(accountId).get());
//
//    }
//}