package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountServiceImplTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    private AccountServiceImpl accountService ;

    @Test
    @Rollback(value = false)
    void increaseDepositFromAccount() {
        Account account = accountRepository.findById(462).get();
        Double initialValue = account.getDeposit();

        account.setDeposit(account.getDeposit() + 200);

        assertThat(initialValue).isEqualTo(account.getDeposit() - 200);

    }

    @Test
    @Rollback(value = false)
    @DisplayName("Update the type of plan by account id")
    void updateTypeOfPlanByAccountId() {
    AccountServiceImpl account = new AccountServiceImpl(userRepository,accountRepository);

        account.updateTypeOfPlanByAccountId(462, "vip_Test");
        Account account1 = accountRepository.findById(462).get();

        assertThat(account1.getType_of_plan()).isEqualTo("vip_Test");
    }

    @Test
    @Rollback(value = false)
    @DisplayName("Increase the savings value from Account entity")
    void increaseSavingsAccount() {

        AccountServiceImpl account = new AccountServiceImpl(userRepository,accountRepository);
        Double accountOld = accountRepository.getAccountById(462).getSavings();

        account.increaseSavingsAccount(10.0, 462);
        Account accountNew = accountRepository.getAccountById(462);

        assertThat(accountOld+10.0).isEqualTo(accountNew.getSavings());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("Increase the balance value with particular value from Account entity")
    void addValueToBalance() {
        AccountServiceImpl accountService1 = new AccountServiceImpl(userRepository,accountRepository);
        Double oldValue = accountRepository.getAccountById(462).getBalance();

        accountService1.addValueToBalance(5.0,462);
        Account accountNew = accountRepository.getAccountById(462);

        assertThat(accountNew.getBalance()).isEqualTo(oldValue + 5.0);
    }
}