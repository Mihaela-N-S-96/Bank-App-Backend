package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.payload.request.LoginRequest;
import com.spring.test.SpringSecurity_JWT_test.payload.request.SignupRequest;
import com.spring.test.SpringSecurity_JWT_test.service.AuthService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;

import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/bank/auth")
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST},
//        allowCredentials = "true", allowedHeaders = {"Content-Type", "Authorization", "X-XSRF-TOKEN","X-CSRF-TOKEN"})
public class AuthController{

    private static final String CSRF_TOKEN_ATTR_NAME = "_csrf";
    private static final String CSRF_TOKEN_HEADER_NAME = "X-XSRF-TOKEN";
    private static final Logger logger =  LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @GetMapping("/csrf")
    public CsrfToken csrfToken(CsrfToken token) {
        return token;
    }

    @PostMapping("/signin")//CSRF-ON; authentication-OFF
     public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest,
                                                @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                                HttpSession session) throws Exception {//, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfTokenHeader, HttpSession session

        CsrfToken sessionToken = (CsrfToken) session.getAttribute(CSRF_TOKEN_ATTR_NAME);

        if (!sessionToken.getToken().equals(csrfToken)) {
            throw new Exception("Invalid CSRF token");
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
    public ResponseEntity<?> registerUserAndSendOtp(@RequestBody SignupRequest signUpRequest , @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfTokenHeader, HttpSession session) {//
        String sessionToken = ((CsrfToken) session.getAttribute(CSRF_TOKEN_ATTR_NAME)).getToken();

        if (!sessionToken.equals(csrfTokenHeader)) {
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
    public ResponseEntity<?> validateOtp(@RequestParam String otpnum, @RequestParam String email, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfTokenHeader, HttpSession session) {
        String sessionToken = ((CsrfToken) session.getAttribute(CSRF_TOKEN_ATTR_NAME)).getToken();

        if (!sessionToken.equals(csrfTokenHeader)) {
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
      public ResponseEntity<?> resendOtp(@RequestParam  String email, @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfTokenHeader, HttpSession session){
        String sessionToken = ((CsrfToken) session.getAttribute(CSRF_TOKEN_ATTR_NAME)).getToken();

        if (!sessionToken.equals(csrfTokenHeader)) {
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
