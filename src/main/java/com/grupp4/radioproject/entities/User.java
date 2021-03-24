package com.grupp4.radioproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("friends")
    private List<User> friends;

    @OneToMany
    //@JsonIgnoreProperties("programFavourites")
    @JsonIgnore
    private List<Program> programFavourites;

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

    public List<User> getFriends() {
        return friends;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Program> getProgramFavourites() {
        return programFavourites;
    }

    public void setProgramFavourites(List<Program> programFavourites) {
        this.programFavourites = programFavourites;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
