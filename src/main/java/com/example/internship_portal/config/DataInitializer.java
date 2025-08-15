package com.example.internship_portal.config;

import com.example.internship_portal.model.users.User;
import com.example.internship_portal.model.users.Role;
import com.example.internship_portal.model.users.Admin;
import com.example.internship_portal.model.users.Mentor;
import com.example.internship_portal.model.users.Intern;
import com.example.internship_portal.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("=== DataInitializer starting ===");
        System.out.println("Current user count: " + userRepo.count());
        
        // Run if no users exist
        if (userRepo.count() == 0) {
            System.out.println("Initializing data...");
            initializeUsers();
            System.out.println("Data initialization complete!");
        } else {
            System.out.println("Users already exist, skipping initialization.");
        }
    }
    
    private void initializeUsers() {
        // Create Admin user
        User admin = new User("admin", passwordEncoder.encode("admin123"), "admin@example.com", "System Administrator");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(new Admin(admin, Role.RoleName.ADMIN, "Tech Corp"));
        admin.setRoles(adminRoles);
        userRepo.save(admin);
        
        // Create Mentor user
        User mentor = new User("mentor", passwordEncoder.encode("mentor123"), "mentor@example.com", "John Mentor");
        Set<Role> mentorRoles = new HashSet<>();
        mentorRoles.add(new Mentor(mentor, Role.RoleName.MENTOR, "Tech Corp", "Senior Developer"));
        mentor.setRoles(mentorRoles);
        userRepo.save(mentor);
        
        // Create Intern user
        User intern = new User("intern", passwordEncoder.encode("intern123"), "intern@example.com", "Alice Intern");
        Set<Role> internRoles = new HashSet<>();
        internRoles.add(new Intern(intern, Role.RoleName.INTERN, "University of Tech", new HashSet<>()));
        intern.setRoles(internRoles);
        userRepo.save(intern);
    }
}
