package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserDetailRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;


    public Account getAccountFromUser(User user, int index){

        List<Account> account = user.getAccount();

       return account.get(index);

    }

     public User saveUser(User user){

        UserDetail userDetail = user.getUserDetail();
        userDetail.setUser(user);

         if(user.getAccount().size() == 2) {
             Account account = getAccountFromUser(user, 0);
             account.setUser(user);
             account = getAccountFromUser(user, 1);
             account.setUser(user);
         }
         else if(user.getAccount().size() == 1){
             Account account = getAccountFromUser(user, 0);
             account.setUser(user);
         }else
         if(user.getAccount().size() == 0)

             throw new RuntimeException("Please, set minim one account!");

         user = userRepository.save(user);
        return user;
    }



    public Optional<User> findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public HashMap<String, Object> getUserLoginResponse(Integer id_user){

        User user = new User();
        user = userRepository.findById(id_user).get();
        String name = user.getUsername();
        String email = user.getEmail();

        UserDetail userDetail = new UserDetail();
        userDetail = userDetailRepository.getUserDetailsByUserId(id_user);

        ArrayList<Account> account = new ArrayList<>();
        account = accountRepository.findByUserId(id_user);

        AccountSerializer serializer = new AccountSerializer();

        HashMap<String, Object> userResponse = new HashMap<>();
        userResponse.put("id", id_user);
        userResponse.put("name", name);
        userResponse.put("email", email);
        userResponse.put("userDetail", userDetail);
        userResponse.put("account", serializer.deserializeList(account));
        return new HashMap<>(userResponse);

    }

    public void editUserDetailsByUserId(Integer id, UserDetail userDetail){
        UserDetail userDetailObj = new UserDetail();
        userDetailObj = userDetailRepository.getUserDetailsByUserId(id);

        userDetailObj.setFirst_name(userDetail.getFirst_name());
        userDetailObj.setLast_name(userDetail.getLast_name());
        userDetailObj.setCountry(userDetail.getCountry());
        userDetailObj.setBirthday(userDetail.getBirthday());
        userDetailObj.setAddress(userDetail.getAddress());
        userDetailObj.setGender(userDetail.getGender());
        userDetailObj.setMobile(userDetail.getMobile());
        userDetailObj.setCreated_at(userDetail.getCreated_at());

        userDetailRepository.saveAndFlush(userDetailObj);
    }

    @Transactional
    public void editUserPassword(User user){
        if(!userRepository.existsByUsername(user.getUsername())){

            throw new RequestException("This username dose not exist!");
        }

        userRepository.changeUserPassword(user.getUsername(), passwordEncoder.encode(user.getPassword()));

    }

    public List<User> getAllUsers(){

        List<User> response = userRepository.findAll();

        if(response.isEmpty()) throw new RequestException("This list is empty");
            else return response;

    }
}

