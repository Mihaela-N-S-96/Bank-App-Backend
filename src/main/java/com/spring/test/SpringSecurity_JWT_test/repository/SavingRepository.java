package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Integer> {
    @Modifying(flushAutomatically = true)
    Saving save(Saving saving);
    public List<Saving> findByDetailsAndAccountId(String details, Integer account_id);
    public Saving findOneById(Integer id);
    public ArrayList<Saving> getAllSavingsByAccountId(Integer account_id);

}
