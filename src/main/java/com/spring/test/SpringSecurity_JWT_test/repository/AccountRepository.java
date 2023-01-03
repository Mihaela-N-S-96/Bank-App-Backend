package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    public User findByEmail(String email);
    @Modifying
    @Query("UPDATE accounts a SET a.savings = a.savings + :savings where a.user_id = :id")
    public void updateSavingsAccount(@Param(value= "savings") Float savings, Long id);
}
