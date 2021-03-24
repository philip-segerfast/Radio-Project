package com.grupp4.radioproject.controllers;

import com.grupp4.radioproject.configurations.MyUserDetailsService;
import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.entities.User;
import com.grupp4.radioproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping("/auth/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/auth/whoami")
    public User whoAmI() {
        return userService.whoAmI();
    }

    @GetMapping("/rest/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/rest/users/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/rest/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/rest/users/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        return userService.updateById(id, user);
    }

    @PutMapping("/rest/user/add-friend/{id}")
    public User addFriend(@PathVariable long id){
        return userService.addFriend(id);
    }

    @DeleteMapping("/rest/user/delete-friend/{id}")
    public User deleteFriend(@PathVariable long id) {
        return userService.deleteFriend(id);
    }

    @PostMapping("/rest/user/add-programfavourite/{id}")
    public boolean addProgramFavourite(@PathVariable long id) {
        userService.addProgramFavourite(id);
        return true;
    }

    @GetMapping("/rest/user/friends")
    public List<User> getFriends() {
        return userService.getFriends();
    }

    @GetMapping("/rest/program-favourites")
    public List<Program> getProgramFavourites() {
        return userService.getProgramFavourites();
    }
}

