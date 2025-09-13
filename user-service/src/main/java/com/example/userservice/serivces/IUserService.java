package com.example.userservice.serivces;

import com.example.userservice.models.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
}

