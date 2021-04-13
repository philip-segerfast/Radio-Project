package com.grupp4.radioproject.services;

import com.grupp4.radioproject.configurations.MyUserDetailsService;
import com.grupp4.radioproject.entities.Channel;
import com.grupp4.radioproject.entities.Episode;
import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.entities.User;
import com.grupp4.radioproject.repositories.ProgramRepo;
import com.grupp4.radioproject.repositories.UserRepo;
import com.grupp4.radioproject.utils.ConsoleColor;
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
    private ProgramService programService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private ProgramCategoryService programCategoryService;

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
        if(program != null) { ///////////////////////////////// SKA INTE DETTA VARA if(program == null)?
            channelService.registerChannel(program.getChannel());
            programCategoryService.registerProgramCategory(program.getProgramCategory());
            programService.registerProgram(program);
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
        if(!doesUserHaveProgramAsFavourite(program)) {
            // userRepo.saveFavouriteProgram(user.getId(), program.getId());
            user.getProgramFavourites().add(program);
            user = userRepo.save(user);
            printInfo("New Favourite program registered in database for user " + user.getUsername() + ".");
            return true;
        }
        return false;
    }

    private boolean doesUserHaveProgramAsFavourite(Program program) {
        List<Program> favourites = getProgramFavourites();
        for(Program potentialFavourite : favourites) {
            if(potentialFavourite.getId() == program.getId()) {
                return true;
            }
        }
        return false;
    }

    public List<Program> getProgramFavourites() {
        User loggedUser = whoAmI();
        return programService.feedProgramInfo(loggedUser.getProgramFavourites());
    }

    public boolean addEpisodeFavourite(long episodeId) {
        User loggedUser = whoAmI();

        Episode episode = episodeService.getEpisodeById(episodeId);
        if(episode != null) {
            programService.registerProgram(episode.getProgram());
            episode = episodeService.registerEpisode(episode);
            return registerFavouriteEpisode(episode, loggedUser);
        } else {
            printError("Tried to save episode that didn't exist in API as favourite for user " + loggedUser.getUsername() + ".");
        }
        return false;
    }

    private boolean registerFavouriteEpisode(Episode episode, User loggedUser) {
        if(!doesUserHaveEpisodeAsFavourite(episode)) {
            loggedUser.getEpisodeFavourites().add(episode);
            loggedUser = userRepo.save(loggedUser);
            printInfo("New Favourite episode registered in database for user " + loggedUser.getUsername() + ".");
            return true;
        }

        return false;
    }

    private boolean doesUserHaveEpisodeAsFavourite(Episode episode) {
        List<Episode> favourites = getEpisodeFavourites();
        for(Episode potentialFavourite : favourites) {
            if(potentialFavourite.getEpisodeId() == episode.getEpisodeId()) {
                return true;
            }
        }
        return false;
    }

    public List<Episode> getEpisodeFavourites() {
        User loggedUser = whoAmI();
        return episodeService.feedEpisodeInfo(loggedUser.getEpisodeFavourites());
    }
}