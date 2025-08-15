package com.example.internship_portal.exception.auth;

public class UserAlreadyAuthenticatedException extends RuntimeException {
    public UserAlreadyAuthenticatedException() {
        super("user already authenticated");
    }
}
