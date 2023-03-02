package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {

    public ArrayList<Deposit> getAllDepositsByAccount_Id(Integer account_id);
}
