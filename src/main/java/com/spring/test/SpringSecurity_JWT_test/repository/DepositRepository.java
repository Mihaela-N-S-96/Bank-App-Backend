package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface DepositRepository extends JpaRepository<Deposit, Integer> {

    @Query(value = "SELECT * FROM deposit d WHERE d.account_id = :account_id ", nativeQuery = true)
    public ArrayList<Deposit> getAllDepositsByAccountId(Integer account_id);
}
