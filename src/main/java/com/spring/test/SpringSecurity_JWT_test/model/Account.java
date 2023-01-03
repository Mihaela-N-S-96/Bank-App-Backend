package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "accounts")
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type_of_plan;
    @Column(name="savings")
    private Float savings;

    private Float currency_balance;
    private Date created_at;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long user_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("accounts")
    private User user;

    public Account(){}

    public Account(Long id, String type_of_plan, Float savings, Float currency_balance, Date created_at) {
        this.id = id;
        this.type_of_plan = type_of_plan;
        this.savings = savings;
        this.currency_balance = currency_balance;
        this.created_at = created_at;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType_of_plan() {
        return type_of_plan;
    }

    public void setType_of_plan(String type_of_plan) {
        this.type_of_plan = type_of_plan;
    }

    public Float getCurrency_balance() {
        return currency_balance;
    }

    public void setCurrency_balance(Float currency_balance) {
        this.currency_balance = currency_balance;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Float getSavings() {
        return savings;
    }

    public void setSavings(Float savings) {
        this.savings = savings;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
