package com.example.internship_portal.controller;

import com.example.internship_portal.dto.LoginDTO;
import com.example.internship_portal.model.users.User;
import com.example.internship_portal.service.AuthenticationService;
import com.example.internship_portal.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }



    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.addUser(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginDTO credentials) {

        boolean isAuthenticated = authenticationService.authenticate(credentials);
        if (isAuthenticated) {
            return "User logged in successfully";
        } else {
            return "Invalid username or password";
        }
    }
}
