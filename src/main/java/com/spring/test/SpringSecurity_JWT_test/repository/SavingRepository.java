package com.spring.test.SpringSecurity_JWT_test.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.test.SpringSecurity_JWT_test.model.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;


public interface SavingRepository extends JpaRepository<Saving, Integer> {

    public List<Saving> findByDetails(String details);
    public Saving findOneById(Integer id);
    public ArrayList<Saving> getAllSavingsByAccountId(Integer account_id);


    @Query(value = "SELECT * from savings s WHERE s.id = :id", nativeQuery = true)
    public ArrayList<Saving> getAllSavingsById(Integer id);
    @Modifying
    @Query(value = "UPDATE savings s SET s.transfer = s.transfer + :value WHERE s.id = :id", nativeQuery = true)
    public void addValueToSavingByIdSaving(Integer id, Double value);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE savings s SET s.transfer = s.transfer - :value WHERE s.id = :id")
    public void decreasesValueFromSavings(Double value, Integer id);
}
