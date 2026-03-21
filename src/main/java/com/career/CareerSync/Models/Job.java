package com.career.CareerSync.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
@Data
@Getter
@Setter
@Entity

public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @Column(nullable = false)
    private String jobCategory;
    @Column(nullable = false)
    private JobType jobType;
    @Column(length = 2000)
    private String jobDescription;

    private String jobRequirements;

    private String jobQualifications;

    private String location;

    private Date closingDate;

    @ManyToOne
    @JoinColumn(name = "postedBy")
    private Company company;

    @Column(nullable = false, updatable = false)
    private Timestamp postedDate;

}
