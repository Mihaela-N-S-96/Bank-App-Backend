package com.spring.test.SpringSecurity_JWT_test.repository;


import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

    @Query(value = "SELECT * FROM transfers t WHERE t.account_id = :account_id OR t.to_account_id = :account_id", nativeQuery = true)
    public ArrayList<Transfer> getAllTransfersByAccountId(Integer account_id);



}
