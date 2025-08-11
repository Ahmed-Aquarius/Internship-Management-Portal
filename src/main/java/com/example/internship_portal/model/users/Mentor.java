package com.example.internship_portal.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "mentors")
@NoArgsConstructor
@AllArgsConstructor
public class Mentor extends User {

    @Column(name = "company", length = 60)
    private String companyName;

    @Column(name = "position", length = 80)
    private String position;

}
