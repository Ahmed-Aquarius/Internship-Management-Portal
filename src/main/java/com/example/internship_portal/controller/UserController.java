package com.example.internship_portal.controller;

import com.example.internship_portal.model.users.User;
import com.example.internship_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping()
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/admins")
    public List<User> getAllAdmins() {
        return userService.findAllAdmins();
    }

    @GetMapping("/mentors")
    public List<User> getAllMentors() {
        return userService.findAllMentors();
    }

    @GetMapping("/interns")
    public List<User> getAllInterns() {
        return userService.findAllInterns();
    }

    @GetMapping("{user_id}")
    public User getUserById(@PathVariable Long user_id) {
        return userService.findUserById(user_id);
    }

    @PostMapping()
    public User createUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    @PutMapping("{id}")
    public User updateUserTotally(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.replaceUser(id, updatedUser);
    }

    @PatchMapping("{id}")
    public User updateUserPartially(@PathVariable Long id, @RequestBody User patchPayLoad) {
        return userService.updateUser(id, patchPayLoad);
    }

    @DeleteMapping("{user_id}")
    public void deleteUser(@PathVariable Long user_id) {
        userService.deleteUser(user_id);
    }

}
