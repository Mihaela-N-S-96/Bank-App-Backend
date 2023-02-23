package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.exceptions.RequestException;
import com.spring.test.SpringSecurity_JWT_test.model.*;
import com.spring.test.SpringSecurity_JWT_test.payload.request.LoginRequest;
import com.spring.test.SpringSecurity_JWT_test.payload.request.SignupRequest;
import com.spring.test.SpringSecurity_JWT_test.payload.response.JwtResponse;
import com.spring.test.SpringSecurity_JWT_test.repository.AccountRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.OtpRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.RoleRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import com.spring.test.SpringSecurity_JWT_test.security.jwt.JwtUtils;
import com.spring.test.SpringSecurity_JWT_test.security.service.UserDetailsImpl;
import com.spring.test.SpringSecurity_JWT_test.service.AuthService;
import com.spring.test.SpringSecurity_JWT_test.service.EmailService;
import com.spring.test.SpringSecurity_JWT_test.service.OtpService;
import com.spring.test.SpringSecurity_JWT_test.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;

import javax.mail.MessagingException;
import javax.naming.AuthenticationException;
import javax.transaction.Transactional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bank/auth")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class AuthController {

    private static final Logger logger =  LoggerFactory.getLogger(AuthController.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpRepository otpRepository;



    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest)  {


        ResponseEntity<?> response;
        try {
            response = authService.authenticateSignIn(loginRequest);
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
        return response;
    }

    @PostMapping("/otp")
    public ResponseEntity<?> registerUserAndSendOtp(@RequestBody SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException{

        ResponseEntity<?> response;
        try{
            response = authService.registerUserAndSendOtp(signUpRequest);
        }catch (Exception e){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The user can not be registered.");
        }
        return response;
    }


    @PostMapping("/validate")
    public ResponseEntity<?> validateOtp(@RequestParam String otpnum, @RequestParam String email) {

      ResponseEntity<?> response;
      try{
          response = authService.validateOtp(otpnum, email);
      }catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");
      }

      return response;
    }

    @PostMapping("/resend/otp")
    public ResponseEntity<?> resendOtp(@RequestParam  String email){

        ResponseEntity<?> response;
        try {
           response = authService.resendOtp(email);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");
        }

        return response;
    }
}



//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            logger.warn("Username is already taken!");
//            throw new RequestException("Username is already taken!");
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            logger.warn("Email is already in use!");
//            throw new RequestException("Email is already in use!");
//        }
//
//        // Create new user's details
//        User user = new User(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));
//
//        Set<Role> roles = new HashSet<>();
//        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//        user.setRoles(roles);
//
//
//        List<Account> first = signUpRequest.getAccount();
//        UserDetail userDetail = signUpRequest.getUserDetail();
//
//        user.setUserDetail(userDetail);
//        user.setAccount(first);
//
//        try {
//            userService.saveUser(user);
//        }catch (RuntimeException ex){
//            logger.warn("Success: User was saved!");
//            return   ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
//        }
//        return   ResponseEntity.status(HttpStatus.OK).body("Success");
//    }