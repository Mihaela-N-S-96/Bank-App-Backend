package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "transfer")
    private Double transfer;

     @Column(name = "status")
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;
    @Column(name = "details")
    private String details;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties("deposit")
    private Account account;

    public Deposit(Integer id, Double transfer, String status, Date date, String details) {
        this.id = id;
        this.transfer = transfer;
        this.status = status;
        this.date = date;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTransfer() {
        return transfer;
    }

    public void setTransfer(Double transfer) {
        this.transfer = transfer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
