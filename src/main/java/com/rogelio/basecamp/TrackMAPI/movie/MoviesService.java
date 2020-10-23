package com.rogelio.basecamp.TrackMAPI.movie;

import org.bson.types.ObjectId;

import java.util.List;

public interface MoviesService {
    Movie createMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovie(ObjectId movieId);
    Movie updateMovie(ObjectId movieId, Movie movie);
    void deleteMovie(ObjectId movieId);
    void deleteAllMovies();
}
