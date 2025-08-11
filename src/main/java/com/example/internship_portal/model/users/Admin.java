package com.example.internship_portal.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "admins")
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User {

    @Column(name = "company", length = 60)
    private String companyName;

}
