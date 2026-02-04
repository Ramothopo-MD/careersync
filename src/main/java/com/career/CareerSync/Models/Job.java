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

    @Column(unique = true, nullable = false)
    private String refNo;

    private String title;
    private String companyName;
    private String companyLogo;
    @Column(nullable = false)
    private String jobCategory;
    @Column(nullable = false)
    private JobType jobType;
    @Column(length = 2000)
    private String jobDescription;

    private String jobReq;//requirements

    private String jobQual;//Qualifications

    private String location;//address formate country,province,city,street address

    private Date closingDate;

    @ManyToOne
    @JoinColumn(name = "postedBy")
    private MyUser admin;

    @Column(nullable = false, updatable = false)
    private Timestamp postedDate;

    @PrePersist
    private void onCreate() {
        // Set posted date
        this.postedDate = new Timestamp(System.currentTimeMillis());

        // Safety: if title is less than 3 chars, use entire title
        String prefix = (title != null && title.length() >= 3) ? title.substring(0, 3) : title;
        if (prefix != null) {
            prefix = prefix.toUpperCase();
        } else {
            prefix = "JOB"; // fallback
        }

        // Last 5 digits of timestamp for short unique reference
        long timePart = this.postedDate.getTime() % 100000;

        this.refNo = prefix + "-" + timePart;
    }
}
