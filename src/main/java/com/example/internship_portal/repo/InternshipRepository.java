package com.example.internship_portal.repo;

import com.example.internship_portal.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository extends JpaRepository<Internship,Long> {
}
