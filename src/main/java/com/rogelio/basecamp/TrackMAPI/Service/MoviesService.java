package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Movie;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface MoviesService {
    void createMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovie(ObjectId movieId);
    Movie updateMovie(ObjectId movieId, Movie movie);
    void deleteMovie(ObjectId movieId);
    void deleteAllMovies();
}
