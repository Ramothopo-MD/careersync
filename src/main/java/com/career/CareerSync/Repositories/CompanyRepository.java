package com.career.CareerSync.Repositories;


import com.career.CareerSync.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
