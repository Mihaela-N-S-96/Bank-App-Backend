package com.spring.test.SpringSecurity_JWT_test.service;

import com.spring.test.SpringSecurity_JWT_test.controller.AuthController;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import javax.mail.MessagingException;
import javax.naming.AuthenticationException;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {
    private static final Logger logger =  LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private OtpService otpService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AccountRepository accountRepository;


@Transactional
    public ResponseEntity<?> authenticateSignIn(LoginRequest loginRequest)  {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            ArrayList<Account> userAccount = accountRepository.findByUserId(userDetails.getId());

            if (userAccount.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("No account found for user with ID " + userDetails.getId());
            }

            JwtResponse jwtResponse = new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles,
                    userAccount.get(0).getActive());
//        JwtResponse jwtResponse = new JwtResponse();
            return ResponseEntity.ok(jwtResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Invalid username or password");
        }
    }

    public ResponseEntity<?> registerUserAndSendOtp(SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException{
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            logger.warn("Your username is already in use!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            logger.warn("Email is already in use!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email is already in use!");
        }

        try {
            // Create new user's details
            User user = new User(signUpRequest.getUsername(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
            user.setRoles(roles);

            List<Account> first = signUpRequest.getAccount();
            UserDetail userDetail = signUpRequest.getUserDetail();

            user.setUserDetail(userDetail);
            user.setAccount(first);
            userService.saveUser(user);

            // Generate and send OTP to user's email
            String otp = otpService.generateOTP();
            Otp otpObj = new Otp();
            otpObj.setOtpnum(otp);
            otpObj.setEmail(signUpRequest.getEmail());

            emailService.sendOTPEmail(signUpRequest.getUserDetail().getUser(), otp);
            otpRepository.save(otpObj);

    return ResponseEntity.status(HttpStatus.OK).body("Verify your email");

        } catch (MessagingException e) {

            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {

            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> validateOtp(String otpnum, String email){
        ArrayList<Account> accountsList = new ArrayList<>();
        User userObject = new User();
        Otp otpObject = new Otp();

        try {
            userObject = userRepository.findByEmail(email);
            if(userObject == null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid email");
            }
        }catch (HttpClientErrorException.NotFound ex){
            logger.error("This email dose not exist");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid email");
        }

        try {
            accountsList = accountRepository.findByUserId(userObject.getId());
        }catch (HttpClientErrorException.NotFound ex){
            logger.error("Can not find this user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error3");
        }

        try {
            otpObject = otpRepository.findByOtpnumAndEmail(otpnum, email);
            if(otpObject == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Otp or email is not valid!");
            }
        }catch (HttpClientErrorException.NotFound ex){
            logger.error("Otp or email is not valid");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Otp or email is not valid!");
        }
        String dbOtp = otpObject.getOtpnum();

        // Check if the OTP in the request matches the one in the DB
        if (!otpnum.equals(dbOtp)) {
            logger.warn("Invalid OTP!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid OTP!");
        }

        try {
            for (Account account:accountsList
            ) {
                System.out.println("save account");
                account.setActive(true);
                accountRepository.save(account);
            }
            otpService.clearOTP(otpnum);
        } catch (Exception ex) {
            logger.warn("Otp can not be delete!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Internal error");
        }
        return ResponseEntity.status(HttpStatus.OK).body("The account is active now!");
    }

    public ResponseEntity<?> resendOtp(String email) {

        Integer user_id = userRepository.findByEmail(email).getId();
        ArrayList<Account> account = accountRepository.findByUserId(user_id);

        if(account.get(0).getActive())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This account is already activated");
        else
            try {
                User user = userRepository.findByEmail(email);

                // Generate and send OTP to user's email
                String otp = otpService.generateOTP();
                Otp otpObj = new Otp();
                otpObj.setOtpnum(otp);
                otpObj.setEmail(email);
                emailService.sendOTPEmail(user, otp);
                otpRepository.save(otpObj);
                return ResponseEntity.status(HttpStatus.OK).body(otp);
            } catch (UnsupportedEncodingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
            } catch (MessagingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
            }
    }


}
