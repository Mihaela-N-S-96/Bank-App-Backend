package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    public Account getAccountFromUser(User user, int index){

        List<Account> account = user.getAccount();

       return account.get(index);

    }

     public User saveUser(User user){

        UserDetail userDetail = user.getUserDetail();
        userDetail.setUser(user);

        Account account = getAccountFromUser(user,0);
        account.setUser(user);
        account = getAccountFromUser(user,1);
        account.setUser(user);

        user = userRepository.save(user);
        return user;
    }



    public Optional<User> findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }
}
