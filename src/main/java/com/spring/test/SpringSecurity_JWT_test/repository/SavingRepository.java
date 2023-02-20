package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Integer> {

    public List<Saving> findByDetails(String details);
    public Saving findOneById(Integer id);
    public ArrayList<Saving> getAllSavingsByAccountId(Integer account_id);
//    @Query(value = "SELECT * from savings s WHERE s.id = :id", nativeQuery = true)
//    public ArrayList<Saving> getAllSavingsById(Integer id);

}
