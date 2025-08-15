package com.example.internship_portal.exception.auth;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("Token is expired");
    }
}
