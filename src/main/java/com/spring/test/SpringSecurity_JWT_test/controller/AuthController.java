package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.payload.request.LoginRequest;
import com.spring.test.SpringSecurity_JWT_test.payload.request.SignupRequest;
import com.spring.test.SpringSecurity_JWT_test.service.AuthService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;

import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import org.slf4j.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/bank/auth")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST},
        allowCredentials = "false", allowedHeaders = {"Content-Type", "Authorization", "X-XSRF-TOKEN"})
public class AuthController extends BaseController{

    private static final Logger logger =  LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

   @Autowired
    private CsrfTokenRepository csrfTokenRepository;

    @GetMapping("/csrf")
    public ResponseEntity<String> getLoginPage(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
        String token = csrfToken.getToken();
        csrfTokenRepository.saveToken(csrfToken, request, response);
        System.out.println("token generat= "+token+ "---"+ csrfTokenRepository.loadToken(request));
        return ResponseEntity.ok(token);
    }


    @PostMapping("/csrf/test")//CSRF-ON; authorization-OFF
    public ResponseEntity<String> getUserPage(HttpServletRequest request) {
        if (!isCsrfTokenValid(request)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Hello!");
    }

    @PostMapping("/signin")//CSRF-ON; authentication-OFF
     public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest, HttpServletRequest request)  {
        if (!isCsrfTokenValid(request)) {
            return ResponseEntity.badRequest().build();
        }

        ResponseEntity<?> response;
        try {
            response = authService.authenticateSignIn(loginRequest);
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
        return response;
    }

    @PostMapping("/otp")//CSRF-ON; authentication-OFF
    public ResponseEntity<?> registerUserAndSendOtp(@RequestBody SignupRequest signUpRequest, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException{
        if (!isCsrfTokenValid(request)) {
            return ResponseEntity.badRequest().build();
        }

        ResponseEntity<?> response;
        try{
            response = authService.registerUserAndSendOtp(signUpRequest);
        }catch (Exception e){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The user can not be registered.");
        }
        return response;
    }


    @PostMapping("/validate")//CSRF-ON; authentication-ON
   // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> validateOtp(@RequestParam String otpnum, @RequestParam String email, HttpServletRequest request) {
        if (!isCsrfTokenValid(request)) {
            return ResponseEntity.badRequest().build();
        }

        ResponseEntity<?> response;
      try{
          response = authService.validateOtp(otpnum, email);
      }catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");
      }

      return response;
    }

    @PostMapping("/resend/otp")//CSRF-ON; authentication-ON
   // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> resendOtp(@RequestParam  String email, HttpServletRequest request){
        if (!isCsrfTokenValid(request)) {
            return ResponseEntity.badRequest().build();
        }

        ResponseEntity<?> response;
        try {
           response = authService.resendOtp(email);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");
        }

        return response;
    }
}
