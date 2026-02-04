package com.career.CareerSync.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Security {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String passwordHash;
    private String securityQuestion;
    private String securityAnswerHash;
    @OneToOne(mappedBy = "secInfo")
    private MyUser user;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
