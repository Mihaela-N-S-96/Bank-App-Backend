package com.spring.test.SpringSecurity_JWT_test.payload.response;

import lombok.Data;

import java.util.List;


@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private String email;

    private Boolean active;
    private List<String> roles;

    public JwtResponse() {
    }

    public JwtResponse(String token, Integer id, String username, String email, List<String> roles, Boolean active) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.active = active;
    }

//    public JwtResponse( Integer id, String username, String email, List<String> roles, Boolean active) {
//
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.roles = roles;
//        this.active = active;
//    }


}
