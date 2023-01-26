package com.spring.test.SpringSecurity_JWT_test.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;


public interface SavingRepository extends JpaRepository<Saving, Integer> {


    @Query(value = "SELECT * from savings s WHERE s.account_id = :account_id", nativeQuery = true)
    public ArrayList<Saving> getAllSavingsByAccountId(Integer account_id);
}
