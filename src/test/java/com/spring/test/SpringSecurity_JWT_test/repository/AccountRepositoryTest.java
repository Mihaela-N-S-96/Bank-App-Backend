//package com.spring.test.SpringSecurity_JWT_test.repository;
//
//import com.spring.test.SpringSecurity_JWT_test.model.Account;
//
//import com.spring.test.SpringSecurity_JWT_test.model.User;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.Date;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class AccountRepositoryTest {
//
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    private static Account account;
//
//
//    @Test
//    @Rollback(value = false)
//    @Order(1)
//    @DisplayName("Create user without encrypting password")
//    void createUserProfile(){
//        User saveUser = userRepository.save(new User("Anamaria_Test", "anamariatest@gmail.com", "parola1234"));
//
//        assertThat(saveUser.getPassword()).isEqualTo("parola1234");
//    }
//
//    @Test
//    @Rollback(value = false)
//    @Order(2)
//    void createUserAccount(){
//        Date date = new Date(2020-01-01);
//        User findUser = userRepository.findByEmail("anamariatest@gmail.com");
//        Account account = new Account("ron_Test", 20.3, 40.2, 40.3, "standard_Test", date);
//        account.setUser(findUser);
//        this.account = account;
//
//        account = accountRepository.save(account);
//
//        assertThat(account.getCurrency()).isEqualTo("ron_Test");
//    }
//
//    @Test
//    @Order(3)
//    void findAccountById() {
//
//        Account account = accountRepository.getAccountById(462);
//        assertThat(account.getId()).isEqualTo(462);
//
//    }
//
//    @Test
//    void getAccountById() {
//        Account account = accountRepository.findById(462).get();
//
//        assertThat(account).isNotNull();
//    }
//
//    @Test
//    void findOneByUserIdAndCurrency() {
//        Account account1 = accountRepository.findByUserIdAndCurrency(119, "ron_Test");
//
//        assertThat(account1.getUser_id()).isEqualTo(119);
//    }
//
//    @Test
//    @DisplayName("Find account id by user id and currency")
//    void findByUserIdAndCurrency() {
//        Account account1 =  accountRepository.findByUserIdAndCurrency(119, "ron_Test");
//
//        assertThat(account1.getId()).isEqualTo(462);
//    }
//
//}