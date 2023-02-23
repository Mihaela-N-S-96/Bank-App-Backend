package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


    public Optional<Account> findById(Integer id);


    @Modifying
    @Query("UPDATE accounts a SET a.savings = a.savings + :savings where a.user_id = :id")
    public void updateSavingsAccount(@Param(value= "savings") Double savings, Integer id);//vezi care e param aici TODO:1.

    @Modifying(flushAutomatically = true)
    @Query("UPDATE accounts a SET a.balance = a.balance - :value where a.id = :id")
    public void decreasesValueFromBalance(Double value, Integer id);

    @Modifying
    @Query("UPDATE accounts a SET a.balance = a.balance + :value where a.id = :id")
    public void addValueToBalance(Double value, Integer id);

    @Modifying
    @Query("UPDATE accounts a SET a.balance = a.balance + :value where a.user_id = :id and a.currency = :currency")
    public void addTransferToBalance(Double value, Integer id, String currency);

    @Modifying
    @Query("UPDATE accounts a SET a.type_of_plan = :type_of_plan WHERE a.id = :id_account")
    public void editTypeOfPlanByAccountId(Integer id_account, String type_of_plan);

    @Query(value = "SELECT a.id FROM accounts a where a.user_id = :id and a.currency like :currency")//and a.type_of_plan = ?2
    public Integer findByIdAndTypeOfPlan(@Param(value = "id") Integer id, String currency);//, String type_of_plan

//    @Query(value = "SELECT a.id, a.balance, a.created_at, a.currency, a.deposit, a.savings, a.type_of_plan, a.user_id FROM accounts a " +
//            "WHERE a.user_id = :id", nativeQuery = true)//and a.type_of_plan = ?2
    public ArrayList<Account> findByUserId(@Param(value = "id") Integer id);//, String type_of_plan


    @Modifying(flushAutomatically = true)
    @Query("UPDATE accounts a SET a.deposit = a.deposit + :value WHERE a.id = :account_id")
    public void addValueToDeposit(Double value, Integer account_id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE accounts a SET a.deposit = a.deposit - :value WHERE a.id = :account_id")
    public void decreasesValueFromDeposit(Double value, Integer account_id);

//    @Modifying(flushAutomatically = true)
//    @Query("UPDATE accounts a SET a.savings = a.savings + :value WHERE a.id = :account_id")
//    public void addValueToSavings(Double value, Integer account_id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE accounts a SET a.savings = a.savings - :value WHERE a.id = :account_id")
    public void decreasesValueFromSavings(Double value, Integer account_id);

    @Modifying(flushAutomatically = true)
    @Query(value = "DELETE FROM accounts s WHERE s.id = :id")
    void deleteByIdWithCascade(Integer id);

    @Query(value = "SELECT * FROM accounts a WHERE a.user_id = :user_id AND a.currency = :currency", nativeQuery = true)
    public Account findOneByUserId(Integer user_id, String currency);

    @Query(value = "SELECT u_details.first_name FROM user_details u_details INNER JOIN users u ON u.id = u_details.user_id\n" +
            "WHERE u.email = :email", nativeQuery = true)
    public String findFirstNameByEmail(String email);

    @Query(value = "select u.first_name from user_details u where u.user_id = :user_id", nativeQuery = true)
    public String findFirstNameByUserId(Integer user_id);

    @Query(value = "select a.user_id from accounts a where a.id = :account_id", nativeQuery = true)
    public Integer getUserIdByAccountId(Integer account_id);

}
