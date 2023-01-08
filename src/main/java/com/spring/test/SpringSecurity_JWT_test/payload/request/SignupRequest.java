package com.spring.test.SpringSecurity_JWT_test.payload.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.spring.test.SpringSecurity_JWT_test.model.Account;
import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;

import java.util.List;
import java.util.Set;

//@JsonDeserialize(using = SignupRequestDeserializer.class)
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private Set<String> role;
    @JsonProperty("userDetail")
    private UserDetail userDetail;
    @JsonProperty("account")
     private List<Account> account;

     public SignupRequest(){}
    public SignupRequest(String username, String email, String password, UserDetail userDetail, List<Account> account) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userDetail = userDetail;
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", userDetail=" + userDetail +
                ", account=" + account +
                '}';
    }
}
