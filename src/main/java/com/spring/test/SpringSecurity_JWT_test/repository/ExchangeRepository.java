package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

    public ArrayList<Exchange> getAllExchangesByAccountId(Integer account_id);

}
