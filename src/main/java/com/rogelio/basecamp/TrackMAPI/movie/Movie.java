package com.rogelio.basecamp.TrackMAPI.movie;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "movies")
public class Movie {

    @Id
    private ObjectId movieId;

    @NotNull
    private String movieName;

    @NotNull
    private String movieDescription;

    @NotNull
    private String directedBy;

    @NotNull
    private String composer;

    @NotNull
    private String dateReleased;

    @NotNull
    private List<String> actors;

    @NotNull
    private String runningTime;

    @NotNull
    private List<String> productionCompany;

    @NotNull
    private String distributedBy;

    @NotNull
    private String coverArtLink;

    @NotNull
    private List<String> writers;

    @NotNull
    private String genre;

    public Movie(){}

    /*
    public Movie(String movieName, String movieDescription, String composer){
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.composer = composer;
    }*/

    /*
    public Movie(String movieName,
                 String movieDescription,
                 String composer,
                 String directedBy,
                 String dateReleased,
                 List<String> actors,
                 String runningTime,
                 List<String> productionCompany,
                 String distributedBy,
                 String coverArtLink,
                 List<String> writers,
                 String genre){
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.composer = composer;
        this.directedBy = directedBy;
        this.dateReleased = dateReleased;
        this.actors = actors;
        this.runningTime = runningTime;
        this.productionCompany = productionCompany;
        this.distributedBy = distributedBy;
        this.coverArtLink = coverArtLink;
        this.writers = writers;
        this.genre = genre;
    }*/

    //region Getters and Setters
    public void setMovieId(ObjectId movieId) {
        this.movieId = movieId;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getMovieId() {
        return movieId.toHexString();
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public String getComposer() {
        return composer;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    public String getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(String dateReleased) {
        this.dateReleased = dateReleased;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getDistributedBy() {
        return distributedBy;
    }

    public void setDistributedBy(String distributedBy) {
        this.distributedBy = distributedBy;
    }

    public String getCoverArtLink() {
        return coverArtLink;
    }

    public void setCoverArtLink(String coverArtLink) {
        this.coverArtLink = coverArtLink;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(List<String> productionCompany) {
        this.productionCompany = productionCompany;
    }
    //endregion
}
