package com.grupp4.radioproject.controllers;

import com.grupp4.radioproject.configurations.MyUserDetailsService;
import com.grupp4.radioproject.entities.Episode;
import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.entities.User;
import com.grupp4.radioproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.grupp4.radioproject.utils.PrintUtils.printDebug;

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
    public Map<String, ?> addProgramFavourite(@PathVariable long id) {
        if(userService.whoAmI() != null) {
            return Collections.singletonMap("success", userService.addProgramFavourite(id));
        }
        return Collections.singletonMap("error", "Could not add program as favourite. Maybe it's already a favourite?");
    }

    @PutMapping("/rest/user/remove-program-favourite/{id}")
    public boolean removeProgramFavourite(@PathVariable long id) {
        return userService.removeProgramFavourite(id);
    }

    @GetMapping("/rest/program-favourites")
    public List<Program> getProgramFavourites() {
        return userService.getProgramFavourites();
    }

    @GetMapping("/rest/is-program-favourite/{id}")
    public Map<String, ?> isProgramFavourite(@PathVariable long id) {
        if(userService.whoAmI() != null) {
            return Collections.singletonMap("isFavourite", userService.isProgramFavourite(id));
        }
        return Collections.singletonMap("error", "You must be logged in to get favourites.");
    }

    @PutMapping("/rest/user/add-episode-favourite/{id}")
    public Map<String, ?> addEpisodeFavourite(@PathVariable long id) {
        if(userService.whoAmI() != null) {
            return Collections.singletonMap("success", userService.addEpisodeFavourite(id));
        }
        return Collections.singletonMap("error", "Could not add episode as favourite. Maybe it's already a favourite?");
    }

    @PutMapping("/rest/user/remove-episode-favourite/{id}")
    public Map<String, ?> removeEpisodeFavourite(@PathVariable long id) {

        if(userService.whoAmI() != null) {
            return Collections.singletonMap("success", userService.removeEpisodeFavourite(id));
        }
        return Collections.singletonMap("error", "Could not add episode as favourite. Maybe it's already a favourite?");
    }

    @GetMapping("/rest/episode-favourites")
    public List<Episode> getEpisodeFavourites() {
        return userService.getEpisodeFavourites();
    }

    @GetMapping("/rest/is-episode-favourite/{id}")
    public Map<String, ?> isEpisodeFavourite(@PathVariable long id) {
        if(userService.whoAmI() != null) {
            boolean isEpisodeFavourite = userService.isEpisodeFavourite(id);
            printDebug("" + isEpisodeFavourite);
            return Collections.singletonMap("isFavourite", isEpisodeFavourite);
        }
        return Collections.singletonMap("error", "You must be logged in to get favourites.");
    }

    /** Get boolean of wether the episode with the ID is a favourite of the currently logged in user */
    @GetMapping("/rest/episode-favourites/{id}")
    public List<Episode> getEpisodeFavourites(@PathVariable long id) {
        return userService.getEpisodeFavourites();
    }

    @GetMapping("/rest/user/friends")
    public List<User> getFriends() {
        return userService.getFriends();
    }
}
