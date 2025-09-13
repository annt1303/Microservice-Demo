package com.example.userservice.controllers;

import com.example.userservice.models.User;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.serivces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    @GetMapping
    public List<User> all() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
