package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(value = {"account"})
@Entity(name= "loans")
@Table(name = "loans")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "loan")
    private Double loan;

    @Column(name = "total_paid")
    private Double total_paid = 0.0;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @Column(name = "details")
    private String details;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "years")
    private Integer years;

    @Column(name = "account_id", insertable = false, updatable = false)
    private Integer account_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties("loans")
    private Account account;


    public Loan (){}


}
