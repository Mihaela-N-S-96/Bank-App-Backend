package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"account"})
@Entity(name = "deposit")
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

    public Deposit() {
    }

    public Deposit(Integer id, Double transfer, String status, Date date, String details) {
        this.id = id;
        this.transfer = transfer;
        this.status = status;
        this.date = date;
        this.details = details;
    }


}
