package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


    Account getAccountById(Integer id);
    Account findByUserIdAndCurrency(Integer user_id, String currency);


//    @Query(value = "SELECT a.id, a.balance, a.created_at, a.currency, a.deposit, a.savings, a.type_of_plan, a.active, a.user_id FROM accounts a " +
//            "WHERE a.user_id = :id", nativeQuery = true)
    ArrayList<Account> findByUserId(@Param(value = "id") Integer id);








}
