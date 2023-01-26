package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

    @Query(value = "SELECT * from exchanges e WHERE e.account_id = :account_id", nativeQuery = true)
    public ArrayList<Exchange> getAllExchangesByAccountId(Integer account_id);

}
