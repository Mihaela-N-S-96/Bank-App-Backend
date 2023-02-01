package com.spring.test.SpringSecurity_JWT_test.repository;

import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

    @Query(value = "SELECT * FROM user_details u where u.user_id = :id", nativeQuery = true)
    public UserDetail getUserDetailsByUserId(@Param(value = "id") Integer id);
}
