package com.career.CareerSync.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Data
@Getter
@Setter
@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String lastName;
    @Column(nullable = false)
    private Role role;
    @Column(unique = true,nullable = false)
    private String email;
    private String profileUrl;
    @Column(nullable = false,unique = true)
    private String phoneNumber;
    @OneToOne
    private Security secInfo;
    @OneToMany
    private List<Company> company;
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }
}
