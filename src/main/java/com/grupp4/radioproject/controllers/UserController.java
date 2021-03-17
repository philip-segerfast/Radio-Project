package com.grupp4.radioproject.controllers;

import com.grupp4.radioproject.entities.User;
import com.grupp4.radioproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/auth/whoami")
    public User whoAmI() {
        return userService.whoAmI();
    }

    @PostMapping("/auth/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
}

