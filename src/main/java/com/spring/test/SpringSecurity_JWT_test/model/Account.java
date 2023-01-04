package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

@Entity(name = "accounts")
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="type_of_plan")
    private String type_of_plan;
    @Column(name="savings")
    private Float savings;

    @Column(name="currency_balance")
    private Float currency_balance;

    @Column(name="created_at")
    private Date created_at;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long user_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("accounts")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.EAGER)
    private List<Loan> loans = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Withdrawal> withdrawals = new ArrayList<>();

    public Account(Long id, String type_of_plan, Float savings, Float currency_balance, Date created_at) {
        this.id = id;
        this.type_of_plan = type_of_plan;
        this.savings = savings;
        this.currency_balance = currency_balance;
        this.created_at = created_at;
    }

public Account(){}


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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

//    public List<Withdrawal> getWithdrawals() {
//        return withdrawals;
//    }
//
//    public void setWithdrawals(List<Withdrawal> withdrawals) {
//        this.withdrawals = withdrawals;
//    }


}