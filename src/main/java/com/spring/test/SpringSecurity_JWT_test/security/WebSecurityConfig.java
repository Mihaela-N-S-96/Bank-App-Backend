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

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;


@Configuration
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors( c ->{
//            CorsConfigurationSource cs = r ->{
//
//                CorsConfiguration cc = new CorsConfiguration();
////                cc.setAllowedOrigins(List.of("http://localhost:3000"));
////                cc.setAllowedMethods(List.of("GET", "POST", "DELETE", "PATCH"));
//                cc.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//                cc.setAllowedMethods(Collections.singletonList("*"));
//                cc.setAllowCredentials(true);
//
//                return cc;
//            };
//                 c.configurationSource(cs);
//        });

        http.cors().and().csrf().disable()
//                .requiresChannel().anyRequest().requiresSecure().and()//new added
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/bank/auth/**").permitAll()
                .antMatchers("/bank/test/**").permitAll()
                .antMatchers("/accounts/**").permitAll()
                .antMatchers("/loans/**").permitAll()
                .antMatchers("/withdrawals/**").permitAll()
                .antMatchers("/exchanges/**").permitAll()
                .antMatchers("/transfers/**").permitAll()
                .antMatchers("/deposit/**").permitAll()
                .antMatchers("/savings/**").permitAll()
                .anyRequest().authenticated();
//.antMatchers("/user/**").permitAll()
        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}