package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;



@JsonIgnoreProperties(value = {"account"})
@Entity(name = "savings")
@Table(name = "savings")
@Data
public class Saving {
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

    @Column(name = "account_id", insertable = false, updatable = false)
    private Integer account_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties("savings")
    private Account account;

    public Saving() {
    }

    public Saving(Integer id, Double transfer, String status, Date date, String details) {
        this.id = id;
        this.transfer = transfer;
        this.status = status;
        this.date = date;
        this.details = details;
    }



}
