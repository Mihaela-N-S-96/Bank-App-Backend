package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import com.spring.test.SpringSecurity_JWT_test.security.service.UserDetailsImpl;
import com.spring.test.SpringSecurity_JWT_test.service.AccountServiceImpl;
import com.spring.test.SpringSecurity_JWT_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/all")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> getById(@PathVariable Integer id){

        return new ResponseEntity<>(userService.getUserLoginResponse(id), HttpStatus.OK);
    }

    @PutMapping("/edit/credentials")
    public ResponseEntity<String> updateUserPassword(@RequestBody User user){

        userService.editUserPassword(user);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<String> editUserDetails(@RequestParam Integer id, @RequestBody UserDetail userDetail){

        userService.editUserDetailsByUserId(id, userDetail);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
}
