package com.sabetski.edi.repository;

import com.sabetski.edi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}