package com.spring.test.SpringSecurity_JWT_test.repository;



import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    public Loan save(Loan loan);
    @Query("SELECT SUM(l.rate) FROM loans l where l.account_id = :id")
    public Double getSumOfRates(@Param(value = "id") Integer id);


}
