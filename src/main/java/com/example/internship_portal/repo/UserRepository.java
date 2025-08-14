package com.example.internship_portal.repo;

import com.example.internship_portal.model.users.User;
import com.example.internship_portal.model.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    
    @Query("SELECT DISTINCT u FROM User u JOIN u.roles r WHERE r.role = :roleName")
    List<User> findUsersByRole(@Param("roleName") Role.RoleName roleName);
}
