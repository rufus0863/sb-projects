package com.demos.userservice.controller;

import com.demos.shareddto.UserDto;
import com.demos.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {

      return userService.getUser(id);
    }
}