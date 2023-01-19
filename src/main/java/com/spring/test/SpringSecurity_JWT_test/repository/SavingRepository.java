package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SavingRepository extends JpaRepository<Saving, Integer> {
}
