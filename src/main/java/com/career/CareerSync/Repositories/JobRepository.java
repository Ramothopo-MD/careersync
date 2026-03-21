package com.career.CareerSync.Repositories;

import com.career.CareerSync.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job,Long> {
    Optional<Job> findByTitle(String title);
}
