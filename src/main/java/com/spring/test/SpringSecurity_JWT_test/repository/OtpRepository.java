package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {

    Otp findByOtpnum(String otpnum);
    Otp findByOtpnumAndEmail(String otpnum, String email);
    @Modifying(flushAutomatically = true)
//    @Query(value = "DELETE FROM otp o WHERE o.email = :email")
    void deleteByOtpnum(String otpnum);

}
