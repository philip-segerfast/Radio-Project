package com.grupp4.radioproject.controllers;

import com.grupp4.radioproject.configurations.MyUserDetailsService;
import com.grupp4.radioproject.entities.Episode;
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

    @PutMapping("/rest/user/add-program-favourite/{id}")
    public boolean addProgramFavourite(@PathVariable long id) {
        return userService.addProgramFavourite(id);
    }

    @GetMapping("/rest/program-favourites")
    public List<Program> getProgramFavourites() {
        return userService.getProgramFavourites();
    }

    @PutMapping("/rest/user/add-episode-favourite/{id}")
    public boolean addEpisodeFavourite(@PathVariable long id) {
        return userService.addEpisodeFavourite(id);
    }

    @GetMapping("/rest/episode-favourites")
    public List<Episode> getEpisodeFavourites() {
        return userService.getEpisodeFavourites();
    }

    @GetMapping("/rest/user/friends")
    public List<User> getFriends() {
        return userService.getFriends();
    }
}
