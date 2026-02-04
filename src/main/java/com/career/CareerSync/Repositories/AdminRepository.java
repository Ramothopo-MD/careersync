package com.career.CareerSync.Repositories;

import com.career.CareerSync.Models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<MyUser,Long> {
}
