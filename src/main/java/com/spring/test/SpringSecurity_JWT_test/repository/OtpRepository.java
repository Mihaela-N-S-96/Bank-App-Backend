package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OtpRepository extends JpaRepository<Otp, Integer> {

    Otp findByEmail(String email);
    @Modifying(flushAutomatically = true)
    @Query(value = "DELETE FROM otp o WHERE o.email = :email")
    void deleteByEmail(String email);

}
