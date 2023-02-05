package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Integer> {

    @Query(value = "SELECT b.id, b.balance, b.date, b.details, b.status , b.account_id FROM balance b WHERE b.account_id = :id", nativeQuery = true)
    public List<Balance> getAllBalanceByAccountId(Integer id);
}
