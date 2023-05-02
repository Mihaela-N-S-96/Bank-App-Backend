package com.spring.test.SpringSecurity_JWT_test.security;

import com.spring.test.SpringSecurity_JWT_test.security.cors.CorsFilter;
import com.spring.test.SpringSecurity_JWT_test.security.csrf.CsrfTokenFilter;
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
import org.springframework.security.web.csrf.*;


@Configuration
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    CsrfTokenFilter csrfTokenFilter;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    static final String[] IGNORE_AUTH_PATTERNS = new String[] {
            "/bank/auth/csrf","/bank/auth/signin","/bank/auth/otp",
            "/bank/auth/validate","/bank/auth/resend/otp","/bank/test/",
            "/accounts/","/loans/","/withdrawals/","/exchanges/","/transfers/",
            "/deposit/","/savings/","/balance/**"};

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


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public Filter csrfHeaderFilter() {
//        return new OncePerRequestFilter() {
//            @Override
//            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                            FilterChain filterChain) throws ServletException, IOException {
//                CsrfToken csrfToken = (CsrfToken) request.getAttribute(CSRF_TOKEN_ATTR_NAME);
//                if (csrfToken != null) {
//                    response.setHeader(CSRF_TOKEN_HEADER_NAME, csrfToken.getToken());
//                    HttpSession session = request.getSession();
//                    session.setAttribute(CSRF_TOKEN_ATTR_NAME, csrfToken);
//                }
//                filterChain.doFilter(request, response);
//            }
//        };
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
        .cors().configurationSource(new CorsFilter())
                .and()
        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .addFilterAfter(csrfTokenFilter, CsrfFilter.class)

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(IGNORE_AUTH_PATTERNS).permitAll()
                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}