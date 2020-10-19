package com.rogelio.basecamp.TrackMAPI.Models;

import java.util.UUID;

public class Games {
    private int gameId;
    private String gameName;
    private String gameDescription;
    private String publisher;

    public Games(int gameId, String gameName, String gameDescription, String publisher) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.publisher = publisher;
    }
    public int getGameId(){
        return this.gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public String getPublisher() {
        return publisher;
    }
}
