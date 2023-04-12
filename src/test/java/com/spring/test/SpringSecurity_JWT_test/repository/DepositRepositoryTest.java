//package com.spring.test.SpringSecurity_JWT_test.repository;
//
//import com.spring.test.SpringSecurity_JWT_test.model.Deposit;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//
//
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
//class DepositRepositoryTest {
//
//
//    @Autowired
//    DepositRepository depositRepository;
//
//
//    @Test
//     void should_store_deposit(){
//        Date date = new Date(2020-01-01);
//
//        Deposit deposit = new Deposit(20,20.3,"for car", date, "detail");
//        depositRepository.save(deposit);
//
//        assertThat(deposit).hasFieldOrPropertyWithValue("id", 20);
//        assertThat(deposit).hasFieldOrPropertyWithValue("transfer", 20.3);
//        assertThat(deposit).hasFieldOrPropertyWithValue("status", "for car");
//        assertThat(deposit).hasFieldOrPropertyWithValue("date", date);
//        assertThat(deposit).hasFieldOrPropertyWithValue("details", "detail");
//
//    }
//
//    @Test
//    @Rollback(false)
//    void test_create_deposit(){
//        Date date = new Date(2020-01-01);
//
//        Deposit saveDeposit =  depositRepository.save(new Deposit(20.3, "for house", date ,"test"));
//
//        assertThat(saveDeposit.getDate()).isEqualTo(date);
//    }
//
//
//    @Test
//    void getAllDepositsByAccountId() {
//        ArrayList<Deposit> depositList = depositRepository.getAllDepositsByAccountId(462);
//
//        assertThat(depositList).isNotNull();
//    }
//}