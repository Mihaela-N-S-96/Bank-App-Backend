package com.spring.test.SpringSecurity_JWT_test.repository;



import com.spring.test.SpringSecurity_JWT_test.model.Loan;
import com.spring.test.SpringSecurity_JWT_test.model.LoanJoinHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    public Loan save(Loan loan);
    @Query(value = "SELECT SUM(l.rate) FROM loans l where l.account_id = :id", nativeQuery = true)
    public Double getSumOfRates(@Param(value = "id") Integer id);

    @Query(value = "SELECT * FROM loans l WHERE l.account_id = :account_id", nativeQuery = true)
    public ArrayList<Loan> getAllLoansByAccountId(Integer account_id);

    @Query(value = "SELECT SUM(l.total_paid) FROM loans l where l.account_id = :id", nativeQuery = true)
    public Double getSumOfPayByAccountId(@Param(value = "id") Integer id);

    @Modifying(flushAutomatically = true)
    @Query(value = "UPDATE loans l SET l.total_paid = :value where l.id = :id")
    public void setTotalPaidById(Integer id, Double value);

    @Query(value = "SELECT new com.spring.test.SpringSecurity_JWT_test.model.LoanJoinHistory(h.id,h.date,l.details,l.total_paid, l.rate) " +
            "FROM loans l " + " JOIN history_loan h ON h.loan_id = l.id")
    List<LoanJoinHistory> findAllJoinHistoryLoan();
}
