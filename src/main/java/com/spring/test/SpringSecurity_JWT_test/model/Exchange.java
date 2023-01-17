package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exchanges")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double exchange;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;
    private String type_exchange;
    private String details;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties("exchanges")
    private Account account;

public Exchange(){}
    public Exchange(Integer id, Double exchange, String type_exchange, Date date, String details) {
        this.id = id;
        this.exchange = exchange;
        this.type_exchange = type_exchange;
        this.date = date;
        this.details = details;
    }

    public Exchange(Integer id, Double exchange, Date date, String details) {
        this.id = id;
        this.exchange = exchange;
        this.date = date;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getExchange() {
        return exchange;
    }

    public void setExchange(Double exchange) {
        this.exchange = exchange;
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

    public String getType_exchange() {
        return type_exchange;
    }

    public void setType_exchange(String type_exchange) {
        this.type_exchange = type_exchange;
    }
}
