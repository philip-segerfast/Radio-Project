package com.grupp4.radioproject.services;

import com.grupp4.radioproject.configurations.MyUserDetailsService;
import com.grupp4.radioproject.entities.User;
import com.grupp4.radioproject.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private MyUserDetailsService detailsService;

    @Autowired
    private UserRepo userRepo;

    /**
     * @return The logged-in user
     */
    public User whoAmI() {
        // SecurityContextHolder.getContext() taps into the current session
        // getAuthentication() returns the current logged in user
        // getName() returns the logged in username (email in this case)
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByUsername(username);
    }

    public User register(User user) {
        return detailsService.registerUser(user);
    }

    public List<User> getAll() {
        return userRepo.findAll(); // SELECT * FROM heroes
    }

    public User getById(long id) {
        Optional<User> user = userRepo.findById(id);

        return user.orElse(null);
    }

    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    public void updateById(long id, User user) {
        User userFromDB = getById(id);
        if (userFromDB != null) {
            user.setId(id);
            // don't update password
            user.setPassword(userFromDB.getPassword());
            userRepo.save(user);
        }
    }



    public void addFriend(long id) {
        User friendToAdd = userRepo.findById(id).orElse(null);
        if (friendToAdd!=null){
            User loggedUser = whoAmI();
            loggedUser.getFriends().add(friendToAdd);
            friendToAdd.getFriends().add(loggedUser);
            userRepo.save(loggedUser);
            userRepo.save(friendToAdd);

        }

    }

    public void deleteFriend(long id) {
        User friendToDelete = userRepo.findById(id).orElse(null);
        if (friendToDelete!=null){
            User loggedUser = whoAmI();
            loggedUser.getFriends().remove(friendToDelete);
            friendToDelete.getFriends().remove(loggedUser);
            userRepo.save(loggedUser);
            userRepo.save(friendToDelete);

        }

    }
}


