package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"account"})
@Entity(name = "transfers")
@Table(name = "transfers")
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

    @Column(name = "account_id", insertable = false, updatable = false)
    private Integer account_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties("exchanges")
    private Account account;

    @Column(name = "from_sender_name")
    private String from_sender_name;

    @Column(name = "to_account_id")
    private Integer to_account_id;

     public Transfer() {
    }

    public Transfer(Integer id, Double transfer, Date date, String details) {
        this.id = id;
        this.transfer = transfer;
        this.date = date;
        this.details = details;
    }


}
