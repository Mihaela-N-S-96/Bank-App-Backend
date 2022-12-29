package com.spring.test.SpringSecurity_JWT_test.repository;


import com.spring.test.SpringSecurity_JWT_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

     public Optional<User> findById(Long id);
}
