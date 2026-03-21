package com.career.CareerSync.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String companyName;
    @Column(nullable = false)
    private String companyLogo;
    @Column(nullable = false,unique = true)
    private String contactNumber;
    @Email
    private String companyEmail;
    private String aboutUs;
    @Column(unique = true)
    private  String registrationNum;
    @Column(nullable = false, updatable = false)
    private Timestamp postedDate;
}
