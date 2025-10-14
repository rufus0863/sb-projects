package com.example.orderservice.service;

import com.example.orderservice.client.UserClient;
import com.example.orderservice.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public UserDto getUser(Long id) {
        return userClient.getUserById(id);
    }
}