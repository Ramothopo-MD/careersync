package com.career.CareerSync.Repositories;

import com.career.CareerSync.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
    Job findByRefNo(String refNo);
    Job findByTitle(String title);
}
