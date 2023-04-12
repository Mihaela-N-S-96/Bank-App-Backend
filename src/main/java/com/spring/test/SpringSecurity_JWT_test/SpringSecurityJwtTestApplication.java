package com.spring.test.SpringSecurity_JWT_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringSecurityJwtTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringSecurityJwtTestApplication.class, args);
	}

}
