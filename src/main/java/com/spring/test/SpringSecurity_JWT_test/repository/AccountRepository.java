package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

//@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    public User findByEmail(String email);

    public Optional<Account> findById(Long id);

    @Modifying
    @Query("UPDATE accounts a SET a.savings = a.savings + :savings where a.user_id = :id")
    public void updateSavingsAccount(@Param(value= "savings") Float savings, Long id);//vezi care e param aici TODO:1.

    @Modifying(flushAutomatically = true)
    @Query("UPDATE accounts a SET a.currency_balance = a.currency_balance - :withdrawal where a.id = :id")
    public void decreasesWithdrawalFromBalance(Float withdrawal, Long id);
}
