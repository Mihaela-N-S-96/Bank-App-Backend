package com.spring.test.SpringSecurity_JWT_test.security;

import com.spring.test.SpringSecurity_JWT_test.security.jwt.AuthEntryPointJwt;
import com.spring.test.SpringSecurity_JWT_test.security.jwt.AuthTokenFilter;
import com.spring.test.SpringSecurity_JWT_test.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;



@Configuration
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

//    @Autowired
//    private CustomCsrfTokenRepository customCsrfTokenRepository;
//@Autowired
//private CsrfTokenRepository csrfTokenRepository;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
//    @Bean
//    public CsrfTokenRepository csrfTokenRepository() {
//        return new CustomCsrfTokenRepository();
//    }
//    @Bean
//    public CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setHeaderName("X-XSRF-TOKEN");
//        return repository;
//    }

//    @Bean
//    public CsrfTokenRepository csrfTokenRepository() {
//        CookieCsrfTokenRepository repository = new CookieCsrfTokenRepository();
//        repository.setCookieName("XSRF-TOKEN");
//        repository.setCookieHttpOnly(false);
//        System.out.println("repository= "+ repository);
//        return repository;
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.cors().disable().csrf()//.disable()
//                .requiresChannel().anyRequest().requiresSecure().and()//new added
                .ignoringAntMatchers("/bank/auth/signin")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                .antMatchers("/bank/auth/csrf").permitAll()
                .antMatchers("/bank/auth/csrf/test").permitAll()
                .antMatchers("/bank/auth/signin").permitAll()
                .antMatchers("/bank/auth/resend/otp").permitAll()
                .antMatchers("/bank/auth/validate").permitAll()

                .antMatchers("/bank/test/**").permitAll()
                .antMatchers("/accounts/**").permitAll()
                .antMatchers("/loans/**").permitAll()
                .antMatchers("/withdrawals/**").permitAll()
                .antMatchers("/exchanges/**").permitAll()
                .antMatchers("/transfers/**").permitAll()
                .antMatchers("/deposit/**").permitAll()
                .antMatchers("/savings/**").permitAll()
                .antMatchers("/balance/**").permitAll()


                .anyRequest().authenticated();
//.antMatchers("/user/**").permitAll()
        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}