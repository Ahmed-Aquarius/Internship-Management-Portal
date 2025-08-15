package com.example.internship_portal.exception.auth;

public class InvalidJwtTokenException extends Exception {
    public InvalidJwtTokenException() {
        super("Invalid JWT token");
    }
}
