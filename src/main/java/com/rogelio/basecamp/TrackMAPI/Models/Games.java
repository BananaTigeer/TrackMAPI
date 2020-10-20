package com.rogelio.basecamp.TrackMAPI.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "games")
public class Games {

    @Id
    private ObjectId gameId;

    private String gameName;
    private String gameDescription;
    private String publisher;

    public Games(String gameName, String gameDescription, String publisher) {
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
