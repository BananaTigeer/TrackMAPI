package com.rogelio.basecamp.TrackMAPI.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movie")
public class Movie {

    @Id
    private ObjectId movieId;

    private String movieName;
    private String movieDescription;
    private String composer;

    public Movie(String movieName, String movieDescription, String composer){
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.composer = composer;
    }

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

}
