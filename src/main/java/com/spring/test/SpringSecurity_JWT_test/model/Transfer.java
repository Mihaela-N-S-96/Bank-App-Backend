package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(value = {"account"})
@Entity
@Table(name="transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "transfer")
    private Double transfer;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @Column(name = "details")
    private String details;

    @Column(name = "to_receiver_name")
    private String to_receiver_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties("exchanges")
    private Account account;


    public String getTo_receiver_name() {
        return to_receiver_name;
    }

    public void setTo_receiver_name(String to_receiver_name) {
        this.to_receiver_name = to_receiver_name;
    }

    //    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "to_account_id")
//    @JsonIgnoreProperties("exchanges")
//    private Account toAccount;

    @Column(name = "from_sender_name")
    private String from_sender_name;
    @Column(name = "to_account_id")
    private Integer to_account_id;

    public Integer getTo_account_id() {
        return to_account_id;
    }

    public void setTo_account_id(Integer to_account_id) {
        this.to_account_id = to_account_id;
    }

    public Transfer() {
    }

    public Transfer(Integer id, Double transfer, Date date, String details) {
        this.id = id;
        this.transfer = transfer;
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

    public String getFrom_sender_name() {
        return from_sender_name;
    }

    public void setFrom_sender_name(String from_sender_name) {
        this.from_sender_name = from_sender_name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
