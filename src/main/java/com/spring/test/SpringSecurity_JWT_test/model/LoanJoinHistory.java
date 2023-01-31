package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class LoanJoinHistory {

    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private String details;
    private Double total_paid;
    private Double rate;


    public LoanJoinHistory(Integer id, Date date, String details, Double total_paid, Double rate) {
        this.id = id;
        this.date = date;
        this.details = details;
        this.total_paid = total_paid;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal_paid() {
        return total_paid;
    }

    public void setTotal_paid(Double total_paid) {
        this.total_paid = total_paid;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}

