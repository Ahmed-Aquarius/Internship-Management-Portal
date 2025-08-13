package com.example.internship_portal.model.users;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "mentors")
@Data
@PrimaryKeyJoinColumn(name = "user-role_id")
public class Mentor extends Role {

    @Column(name = "company", length = 60)
    private String companyName;

    @Column(name = "position", length = 80)
    private String position;



    public Mentor(User user, Role.RoleName role, String companyName, String position) {
        super(user, role);
        this.companyName = companyName;
        this.position = position;
    }

    public Mentor() {
        super(Role.RoleName.MENTOR);
    }



    public String getCompanyName() {
        return companyName;
    }

    public String getPosition() {
        return position;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
