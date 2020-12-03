package com.rogelio.basecamp.TrackMAPI.videogame;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;


@Document(collection = "games")
public class VideoGame {

    @Id
    private ObjectId gameId;

    @NotNull
    private String gameName;

    @NotNull
    private String gameDescription;

    @NotNull
    private String dateReleased;

    @NotNull
    private String publisher;

    @NotNull
    private String developer;

    @NotNull
    private List<String> composer;

    @NotNull
    private String coverArtLink;

    @NotNull
    private String genre;

    @NotNull
    private List<String> modes;

    @NotNull
    private String engine;

    @NotNull
    private String writer;

    public VideoGame(){}

    /*
    public VideoGame(String gameName,
                     String gameDescription,
                     String publisher) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.publisher = publisher;
    }

    public VideoGame(String gameName,
                     String gameDescription,
                     String dateReleased,
                     String developer,
                     String publisher,
                     List<String> composer,
                     String coverArtLink,
                     String genre,
                     List<String> modes,
                     String engine,
                     String writer){

        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.dateReleased = dateReleased;
        this.developer = developer;
        this.publisher = publisher;
        this.composer = composer;
        this.coverArtLink = coverArtLink;
        this.genre = genre;
        this.modes = modes;
        this.engine = engine;
        this.writer = writer;
    }

     */

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

    public String getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(String dateReleased) {
        this.dateReleased = dateReleased;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public List<String> getComposer() {
        return composer;
    }

    public void setComposer(List<String> composer) {
        this.composer = composer;
    }

    public String getCoverArtLink() {
        return coverArtLink;
    }

    public void setCoverArtLink(String coverArtLink) {
        this.coverArtLink = coverArtLink;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getModes() {
        return modes;
    }

    public void setModes(List<String> modes) {
        this.modes = modes;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    //endregion
}
