package com.example.internship_portal.model.users;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "admins")
@Data
@PrimaryKeyJoinColumn(name = "user-role_id")
public class Admin extends Role {

    @Column(name = "company", length = 60)
    private String companyName;


    public Admin(User user, Role.RoleName role, String companyName) {
        super(user, role);
        this.companyName = companyName;
    }

    public Admin() {
        super(Role.RoleName.ADMIN);
    }



    public String getCompanyName() {
        return companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
