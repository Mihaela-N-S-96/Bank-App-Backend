//package com.spring.test.SpringSecurity_JWT_test.service;
//
//import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
//import com.spring.test.SpringSecurity_JWT_test.repository.DepositRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//
//@Service
//public class ValidationsServiceImpl implements ValidationsService{
//
//    private final JpaRepository<?, ?> jpaRepository;
//
//    @Autowired
//    public ValidationsServiceImpl(JpaRepository<?, ?> jpaRepository) {
//        this.jpaRepository = jpaRepository;
//    }
//
//    public <T> void verifyIfIdExists(Integer id, Class<T> clazz) {
//
//        JpaRepository<T, Integer> jpaRepository1 = (JpaRepository<T, Integer>) jpaRepository;
//
//        if (!jpaRepository1.existsById(id)) {
//            throw new RequestException("This id does not exist!");
//        }
//    }
//}