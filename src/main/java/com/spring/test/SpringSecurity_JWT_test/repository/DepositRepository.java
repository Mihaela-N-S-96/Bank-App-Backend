package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Integer> {
}
