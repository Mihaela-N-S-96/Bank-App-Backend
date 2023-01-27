package com.spring.test.SpringSecurity_JWT_test.repository;



import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    public Loan save(Loan loan);
    @Query(value = "SELECT SUM(l.rate) FROM loans l where l.account_id = :id", nativeQuery = true)
    public Double getSumOfRates(@Param(value = "id") Integer id);

    @Query(value = "SELECT * FROM loans l WHERE l.account_id = :account_id", nativeQuery = true)
    public ArrayList<Loan> getAllLoansByAccountId(Integer account_id);

    @Query(value = "SELECT SUM(l.total_paid) FROM loans l where l.account_id = :id", nativeQuery = true)
    public Double getSumOfPayByAccountId(@Param(value = "id") Integer id);
}
