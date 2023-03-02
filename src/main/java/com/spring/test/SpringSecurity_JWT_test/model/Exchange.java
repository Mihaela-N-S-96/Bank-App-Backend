package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"account"})
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


}
