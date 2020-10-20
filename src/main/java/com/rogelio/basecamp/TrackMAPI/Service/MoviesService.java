package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Movie;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface MoviesService {
    abstract void createMovie(Movie movie);
    abstract List<Movie> getAllMovies();
    abstract Optional<Movie> getMovie(ObjectId movieId);
    abstract Movie putMovie(ObjectId movieId, Movie movie);
    abstract Movie patchMovie(ObjectId movieId, Movie movie);
    abstract void deleteMovie(ObjectId movieId);
    abstract void deleteAllMovies();
}
