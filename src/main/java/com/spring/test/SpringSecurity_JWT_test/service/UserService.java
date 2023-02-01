package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Component
public interface UserService {

     public User saveUser(User user);
     public Optional<User> findById(Integer id);
     public HashMap<String, Object> getUserLoginResponse(Integer id_user);
}
