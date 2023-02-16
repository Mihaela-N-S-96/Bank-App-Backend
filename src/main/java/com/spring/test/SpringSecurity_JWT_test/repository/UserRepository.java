package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    User findByAccountId(Integer id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Modifying(flushAutomatically = true)
    @Query(value = "UPDATE users u SET u.password = :passwordEncoded WHERE u.username = :username")
    public void changeUserPassword(String username, String passwordEncoded);


}
