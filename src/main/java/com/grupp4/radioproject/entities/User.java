package com.grupp4.radioproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.ApplicationContext;

import javax.persistence.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "target_user_id"),
            inverseJoinColumns = @JoinColumn(name = "destination_user_id")
    )
    private List<User> friends;

    @ManyToMany
    @JoinTable(
            name = "program_favourites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<Program> programFavourites;

    @ManyToMany
    @JoinTable(
            name = "episode_favourites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id")
    )
    private List<Episode> episodeFavourites;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(long id, List<Program> programFavourites) {
        this.id = id;
        this.programFavourites = programFavourites;
    }

    @JsonIgnore
    public List<User> getFriends() {
        return friends;
    }
    @JsonProperty
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    /**
     * Put @JsonIgnore on the password getter to prevent
     * password leaking to frontend
     * and @JsonProperty on the setter to enable login
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty
    public long getId() {
        return id;
    }
    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }
    @JsonProperty
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty
    public List<Program> getProgramFavourites() {
        return programFavourites;
    }
    @JsonProperty
    public void setProgramFavourites(List<Program> programFavourites) {
        this.programFavourites = programFavourites;
    }

    @JsonProperty
    public List<Episode> getEpisodeFavourites() {
        return episodeFavourites;
    }
    @JsonProperty
    public void setEpisodeFavourites(List<Episode> episodeFavourites) {
        this.episodeFavourites = episodeFavourites;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", friends=" + friends +
                ", programFavourites=" + programFavourites +
                '}';
    }
}
