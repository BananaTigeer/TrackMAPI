package com.rogelio.basecamp.TrackMAPI.movie;

import org.bson.types.ObjectId;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MoviesService {
    Movie createMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovie(HttpServletRequest request, String movieId);
    Movie putMovie(String movieId, Movie movie);
    Movie patchMovie(String movieId, Movie movie);
    String deleteMovie(String movieId);
    String deleteAllMovies();
}
