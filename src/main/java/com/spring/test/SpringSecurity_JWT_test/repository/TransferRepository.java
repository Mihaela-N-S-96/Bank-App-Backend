package com.spring.test.SpringSecurity_JWT_test.repository;


import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

    public ArrayList<Transfer> getAllTransfersByAccountId(Integer account_id);


}
