package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.User;
import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;
import com.spring.test.SpringSecurity_JWT_test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.spring.test.SpringSecurity_JWT_test.controller.AuthController.CSRF_TOKEN_HEADER_NAME;

@RestController
@RequestMapping("/user")
public class UserController extends CsrfController{


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(@RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                  HttpSession session)throws Exception{

        validateCsrfToken(csrfToken, session);
        return userService.getAllUsers();
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> getById(@PathVariable Integer id,
                                          @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                          HttpSession session)throws Exception{

        validateCsrfToken(csrfToken, session);
        return new ResponseEntity<>(userService.getUserLoginResponse(id), HttpStatus.OK);
    }

    @PutMapping("/edit/credentials")
    public ResponseEntity<String> updateUserPassword(@RequestBody User user,
                                                     @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                                     HttpSession session)throws Exception{

        validateCsrfToken(csrfToken, session);
        userService.editUserPassword(user);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<String> editUserDetails(@RequestParam Integer id,
                                                  @RequestBody UserDetail userDetail,
                                                  @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                                  HttpSession session)throws Exception{

        validateCsrfToken(csrfToken, session);
        userService.editUserDetailsByUserId(id, userDetail);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
}
