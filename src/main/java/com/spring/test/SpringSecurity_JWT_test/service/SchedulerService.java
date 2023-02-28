package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SchedulerService {

    @Autowired
    private OtpRepository otpRepository;

    @Scheduled(fixedRate = 300000) //300000 = 5 min, 60000 = 1 min
    public void deleteExpiredOtp() {
        System.out.println("Scheduler started at : " + LocalDateTime.now());

        otpRepository.deleteByCreatedAtBefore(LocalDateTime.now().minusMinutes(5));
    }
}
