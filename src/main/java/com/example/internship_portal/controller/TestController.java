package com.example.internship_portal.controller;

import com.example.internship_portal.model.User;
import com.example.internship_portal.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private UserService userService;

    private ObjectMapper objectMapper;

    @Autowired
    public TestController(UserService theUserService, ObjectMapper theObjectMapper) {
        userService = theUserService;
        objectMapper = theObjectMapper;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

}
