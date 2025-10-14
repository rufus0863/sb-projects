package com.example.orderservice.controller;

import com.example.orderservice.dto.UserDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final UserService userService;

    public OrderController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{orderId}")
    public Map<String, Object> getOrderDetails(@PathVariable Long orderId) {
        Order order = new Order(orderId, 1L, "Laptop", 999.99);
        UserDto user = userService.getUser(order.getUserId());
        Map<String, Object> response = new HashMap<>();
        response.put("order", order);
        response.put("user", user);
        return response;
    }
}