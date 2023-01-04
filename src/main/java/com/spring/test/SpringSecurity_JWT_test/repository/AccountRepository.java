package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Optional;

//@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

//    public User findByEmail(String email);

    public Optional<Account> findById(Integer id);

    @Modifying
    @Query("UPDATE accounts a SET a.savings = a.savings + :savings where a.user_id = :id")
    public void updateSavingsAccount(@Param(value= "savings") Double savings, Integer id);//vezi care e param aici TODO:1.

    @Modifying(flushAutomatically = true)
    @Query("UPDATE accounts a SET a.currency_balance = a.currency_balance - :value where a.id = :id")
    public void decreasesValueFromBalance(Double value, Integer id);

    @Modifying
    @Query("UPDATE accounts a SET a.currency_balance = a.currency_balance + :value where a.id = :id")
    public void addValueToBalance(Double value, Integer id);


    @Query(value = "SELECT a.id FROM accounts a where a.user_id = :id and a.type_of_plan like :type_of_plan")//and a.type_of_plan = ?2
    public Integer findByIdAndTypeOfPlan(@Param(value = "id") Integer id, String type_of_plan);//, String type_of_plan
}
