package com.spring.test.SpringSecurity_JWT_test.repository;


import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
