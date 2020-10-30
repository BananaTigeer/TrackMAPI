package com.rogelio.basecamp.TrackMAPI.user;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Document(collection = "users")
public class User {

    @Id
    private ObjectId userId;

    @Indexed
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String dateRegistered;

    //@NotNull
    private boolean admin;

    @NotNull
    private int moviesWatched;

    @NotNull
    private int gamesPlayed;

    @NotNull
    private int seriesWatched;

    public User(){}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username,
                String password,
                String email,
                String dateRegistered,
                boolean admin,
                int moviesWatched,
                int gamesPlayed,
                int seriesWatched) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateRegistered = dateRegistered;
        this.admin = admin;
        this.moviesWatched = moviesWatched;
        this.gamesPlayed = gamesPlayed;
        this.seriesWatched = seriesWatched;
    }

    //region Getters and Setters
    public String getUserId() {
        return userId.toHexString();
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getMoviesWatched() {
        return moviesWatched;
    }

    public void setMoviesWatched(int moviesWatched) {
        this.moviesWatched = moviesWatched;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getSeriesWatched() {
        return seriesWatched;
    }

    public void setSeriesWatched(int seriesWatched) {
        this.seriesWatched = seriesWatched;
    }

    //endregion
}
