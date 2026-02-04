package com.career.CareerSync.Repositories;

import com.career.CareerSync.Models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser,Long> {
    public Optional<MyUser> findByEmail(String email);
    public Optional<MyUser> findByphoneNumber(String phoneNumber);
}
