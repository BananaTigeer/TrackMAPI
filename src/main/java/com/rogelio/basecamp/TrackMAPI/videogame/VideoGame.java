package com.rogelio.basecamp.TrackMAPI.videogame;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


@Document(collection = "games")
public class VideoGame {

    @Id
    private ObjectId gameId;

    @NotNull
    private String gameName;

    @NotNull
    private String gameDescription;

    @NotNull
    private String publisher;

    public VideoGame(String gameName, String gameDescription, String publisher) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.publisher = publisher;
    }

    //region Getters and Setters
    public String getGameId() {
        return gameId.toHexString();
    }

    public void setGameId(ObjectId gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    //endregion
}
