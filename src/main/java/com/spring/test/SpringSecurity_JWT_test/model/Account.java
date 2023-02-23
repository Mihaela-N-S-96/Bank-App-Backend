package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
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

    @Column(name="balance")
    private Double balance = 0.00;

    @Column(name = "type_of_plan")
    private String type_of_plan;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="created_at")
    private Date created_at;

    @Column(name = "active")
    private Boolean active = false;

    @JsonIgnoreProperties(value = "user_id")
    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer user_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
//    @JsonIgnore //
    private User user;

    @JsonIgnoreProperties(value = {"loans", "user"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.EAGER)
    private List<Loan> loans = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)//acum
    private List<Saving> saving ;
    public Account(String currency, Double savings, Double balance, Double deposit, String type_of_plan, Date created_at) {
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

    public Account() {
    }

}



