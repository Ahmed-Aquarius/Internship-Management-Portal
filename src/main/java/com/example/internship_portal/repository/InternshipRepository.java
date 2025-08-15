package com.example.internship_portal.repository;

import com.example.internship_portal.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository extends JpaRepository<Internship,Long> {
}
