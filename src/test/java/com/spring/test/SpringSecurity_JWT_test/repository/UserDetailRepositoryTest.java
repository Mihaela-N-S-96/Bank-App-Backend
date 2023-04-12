//package com.spring.test.SpringSecurity_JWT_test.repository;
//
//import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class UserDetailRepositoryTest {
//
//    @Autowired
//    UserDetailRepository userDetailRepository;
//
//    @Test
//    void findByUserId() {
//        UserDetail userDetail = userDetailRepository.findByUserId(119);
//
//        assertThat(userDetail.getId()).isEqualTo(74);
//    }
//
//    @Test
//    void findByUserEmail() {
//        UserDetail userDetail = userDetailRepository.findByUserEmail("anamariatest@gmail.com");
//
//        assertThat(userDetail.getId()).isEqualTo(74);
//    }
//}