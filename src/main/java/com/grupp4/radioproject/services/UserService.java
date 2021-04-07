package com.grupp4.radioproject.services;

import com.grupp4.radioproject.configurations.MyUserDetailsService;
import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.entities.User;
import com.grupp4.radioproject.repositories.ProgramRepo;
import com.grupp4.radioproject.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.grupp4.radioproject.utils.PrintUtils.*;

@Service
public class UserService {

    @Autowired
    private MyUserDetailsService detailsService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private ProgramService programService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    DataSource dataSource;

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

    public User updateById(long id, User user) {
        User userFromDB = getById(id);
        if (userFromDB != null) {
            user.setId(id);
            // don't update password
            user.setPassword(userFromDB.getPassword());
            return userRepo.save(user);
        }
        return null;
    }

    public User addFriend(long id) {
        User friendToAdd = userRepo.findById(id).orElse(null);
        if (friendToAdd != null) {
            User loggedUser = whoAmI();
            loggedUser.getFriends().add(friendToAdd);
            friendToAdd.getFriends().add(loggedUser);
            userRepo.save(loggedUser);
            userRepo.save(friendToAdd);
            return loggedUser;
        }
        return null;
    }

    public User deleteFriend(long id) {
        User friendToDelete = userRepo.findById(id).orElse(null);
        if (friendToDelete != null) {
            User loggedUser = whoAmI();
            loggedUser.getFriends().remove(friendToDelete);
            friendToDelete.getFriends().remove(loggedUser);
            userRepo.save(loggedUser);
            userRepo.save(friendToDelete);
            return loggedUser;
        }
        return null;
    }

    public List<User> getFriends() {
        User loggedUser = whoAmI();
        return loggedUser.getFriends();
    }

    public boolean addProgramFavourite(long programId) {
        User loggedUser = whoAmI();
        long loggedUserId = loggedUser.getId();
        String loggedUserName = loggedUser.getUsername();

        Program program = programService.getProgramById(programId);
        if(program != null) {
            long channelId = program.getChannel().getId();

            printDebug("1");
            //1. Check if channel doesn't already exist
            channelService.registerChannel(channelId);

            printDebug("2");
            //2. Check if program doesn't already exist
            programService.registerProgram(program);

            printDebug("3");
            return registerFavouriteProgram(program, loggedUser);

        } else {
            printError("Tried to save program that didn't exist in API as favourite for user " + loggedUserName + ".");
        }
        return false;
    }

    /**
     * Registers a program as a favourite.
     * @return true if new favourite was saved, false if it wasn't saved.
     */
    private boolean registerFavouriteProgram(Program program, User user) {
        if(!doesUserHaveProgramAsFavourite(program.getProgramId())) {
            userRepo.saveFavouriteProgram(user.getId(), program.getProgramId());
            printInfo("New Favourite program registered in database for user " + user.getUsername() + ".");
            return true;
        }
        return false;
    }

    private boolean doesUserHaveProgramAsFavourite(long programId) {
        List<Program> favourites = getProgramFavourites();
        for(Program program : favourites) {
            if(program.getProgramId() == programId) {
                return true;
            }
        }
        return false;
    }

    public List<Program> getProgramFavourites() {
        User loggedUser = whoAmI();

        try {
            Connection conn = DataSourceUtils.doGetConnection(dataSource);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT program_id FROM program_favourites WHERE user_id = ?;");
            preparedStatement.setLong(1, loggedUser.getId());
            ResultSet res = preparedStatement.executeQuery();

            List<Program> favouritePrograms = new ArrayList<>();
            while(res.next()) {
                int programId = res.getInt(1);
                Program program = programService.getProgramById(programId);
                favouritePrograms.add(program);
            }
            return favouritePrograms;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}