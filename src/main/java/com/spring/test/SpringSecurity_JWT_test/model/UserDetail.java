package com.spring.test.SpringSecurity_JWT_test.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_details")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String first_name;
    private String last_name;
    private String country;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="birthday")
    private Date birthday;
    private String address;
    private String gender;
    private String mobile;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    private Date created_at;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public UserDetail(){}

    public UserDetail(String first_name, String last_name, String country, Date birthday, String address, String gender, String mobile, Date created_at) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.country = country;
        this.birthday = birthday;
        this.address = address;
        this.gender = gender;
        this.mobile = mobile;
        this.created_at = created_at;
    }

    public UserDetail(
                       String gender,
                        String mobile ) {


        this.gender = gender;

        this.mobile = mobile;
    }


    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", country='" + country + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", mobile='" + mobile + '\'' +
                ", created_at=" + created_at +
                ", user=" + user +
                '}';
    }
}
