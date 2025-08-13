package com.example.internship_portal.service;

import com.example.internship_portal.dto.LoginDTO;
import com.example.internship_portal.model.users.User;
import com.example.internship_portal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public boolean authenticate(LoginDTO credentials) {
        User candidateUser = userRepository.findByUsername(credentials.username()).orElse(null);    //TODO

        if (passwordEncoder.matches(credentials.password(), candidateUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
