package com.rogelio.basecamp.TrackMAPI.movie;

import org.bson.types.ObjectId;

import java.util.List;

public interface MoviesService {
    Movie createMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovie(String movieId);
    Movie updateMovie(String movieId, Movie movie);
    String deleteMovie(String movieId);
    String deleteAllMovies();
}
