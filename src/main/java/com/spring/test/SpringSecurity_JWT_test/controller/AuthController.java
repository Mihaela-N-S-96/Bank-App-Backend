package com.spring.test.SpringSecurity_JWT_test.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.test.SpringSecurity_JWT_test.model.*;
import com.spring.test.SpringSecurity_JWT_test.payload.request.LoginRequest;
import com.spring.test.SpringSecurity_JWT_test.payload.request.SignupRequest;
import com.spring.test.SpringSecurity_JWT_test.payload.response.JwtResponse;
import com.spring.test.SpringSecurity_JWT_test.payload.response.MessageResponse;
import com.spring.test.SpringSecurity_JWT_test.repository.RoleRepository;
import com.spring.test.SpringSecurity_JWT_test.repository.UserRepository;
import com.spring.test.SpringSecurity_JWT_test.security.jwt.JwtUtils;
import com.spring.test.SpringSecurity_JWT_test.security.service.UserDetailsImpl;
import com.spring.test.SpringSecurity_JWT_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bank/auth")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST},
             allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization"})
public class AuthController {
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

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's details
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        user.setRoles(roles);


        List<Account> first = signUpRequest.getAccount();
        UserDetail userDetail = signUpRequest.getUserDetail();

        user.setUserDetail(userDetail);
        user.setAccount(first);
        userService.saveUser(user);

        return ResponseEntity.ok("ok");
    }

}
