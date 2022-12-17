package com.spring.test.SpringSecurity_JWT_test.repository;


import com.spring.test.SpringSecurity_JWT_test.model.ERole;
import com.spring.test.SpringSecurity_JWT_test.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
