package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Double savings = 0.00;
    @Column(name="deposit")
    private Double deposit = 0.00;
//    @Column(name = "unique")
//    private String unique;

    @Column(name="balance")
    private Double balance = 0.00;

    @Column(name = "type_of_plan")
    private String type_of_plan;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="created_at")
    private Date created_at;

    @JsonIgnoreProperties(value = "user_id")
    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer user_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
//    @JsonIgnore //asta era necesar nu ai stiu unde. Verifica restul responsu ruilor
    private User user;

    @JsonIgnoreProperties(value = {"loans", "user"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.EAGER)
    private List<Loan> loans = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Withdrawal> withdrawals = new ArrayList<>();


    public Account(String currency, Double savings, Double balance,Double deposit, String type_of_plan, Date created_at) {
        this.currency = currency;
        this.savings = savings;
        this.balance = balance;
        this.deposit = deposit;
        this.type_of_plan = type_of_plan;
        this.created_at = created_at;
    }

    public Account(Integer id, String currency, Double savings, Double deposit, Double balance, String type_of_plan, Date created_at) {
        this.id = id;
        this.currency = currency;
        this.savings = savings;
        this.deposit = deposit;
        this.balance = balance;
        this.type_of_plan = type_of_plan;
        this.created_at = created_at;
    }

    public Account(Integer id, String currency, Double savings, Double balance, Date created_at) {
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

    public Double getSavings() {
        return savings;
    }

    public void setSavings(Double savings) {
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


    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

//    public String getUnique() {
//        return unique;
//    }
//
//    public void setUnique(String unique) {
//        this.unique = unique;
//    }
}


