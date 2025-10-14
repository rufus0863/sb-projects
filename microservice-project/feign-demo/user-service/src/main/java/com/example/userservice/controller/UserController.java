package com.example.userservice.controller;

import com.example.userservice.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return new User(id, "Mario Rossi", "mario.rossi@example.com");
    }
}