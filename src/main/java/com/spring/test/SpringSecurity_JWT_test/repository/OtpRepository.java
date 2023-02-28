package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {

    @Modifying
    @Transactional
    void deleteByCreatedAtBefore(LocalDateTime dateTime);
    List<Otp> findByCreatedAtBefore(LocalDateTime dateTime);

    Otp findByOtpnum(String otpnum);
    Otp findByOtpnumAndEmail(String otpnum, String email);
    @Modifying(flushAutomatically = true)
//    @Query(value = "DELETE FROM otp o WHERE o.email = :email")
    void deleteByOtpnum(String otpnum);

}
