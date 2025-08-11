package com.example.internship_portal.model.users;


import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleName role;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    public enum RoleName {
        INTERN, MENTOR, ADMIN
    }

}

