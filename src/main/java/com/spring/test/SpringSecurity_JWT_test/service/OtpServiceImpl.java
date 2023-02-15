package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService{

    @Autowired
    private OtpRepository otpRepository;
    public String generateOTP(){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);

        return Integer.toString(otp);
    }

    @Transactional
    public void clearOTP(String email){
        otpRepository.deleteByEmail(email);
    }

}
