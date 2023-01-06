package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

@Entity(name = "accounts")
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="currency")
    private String currency;
    @Column(name="savings")
    private Float savings;

    @Column(name="balance")
    private Double balance;

    @Column(name = "type_of_plan")
    private String type_of_plan;

    @Column(name="created_at")
    private Date created_at;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer user_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("accounts")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.EAGER)
    private List<Loan> loans = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Withdrawal> withdrawals = new ArrayList<>();

    public Account(Integer id, String currency, Float savings, Double balance, Date created_at) {
        this.id = id;
        this.currency = currency;
        this.savings = savings;
        this.balance = balance;
        this.created_at = created_at;
    }

public Account(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public String getType_of_plan() {
        return type_of_plan;
    }

    public void setType_of_plan(String type_of_plan) {
        this.type_of_plan = type_of_plan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
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
