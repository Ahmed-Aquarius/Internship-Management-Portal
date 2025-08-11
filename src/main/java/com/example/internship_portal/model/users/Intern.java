package com.example.internship_portal.model.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "interns")
@NoArgsConstructor
@AllArgsConstructor
public class Intern extends User {

    @Column(name = "school", length= 50)
    private String School;

    @ElementCollection
    @CollectionTable(
            name = "intern_skills",
            joinColumns = @JoinColumn(name = "intern_id")
    )
    @Column(name = "skill")
    private List<String> skills;

}
