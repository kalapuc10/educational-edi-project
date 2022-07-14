package com.sabetski.edi.repository;

import com.sabetski.edi.entity.UserRole;
import com.sabetski.edi.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}