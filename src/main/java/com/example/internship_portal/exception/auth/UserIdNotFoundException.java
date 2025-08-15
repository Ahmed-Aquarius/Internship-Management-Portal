package com.example.internship_portal.exception.auth;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException() {
        super("User not found");
    }
}
