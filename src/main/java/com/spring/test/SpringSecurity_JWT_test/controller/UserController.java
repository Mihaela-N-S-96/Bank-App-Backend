package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;
import com.spring.test.SpringSecurity_JWT_test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT},
//        allowCredentials = "true", allowedHeaders = {"Content-Type", "Authorization","X-XSRF-TOKEN","X-CSRF-TOKEN"})
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){

        return userService.getAllUsers();
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
