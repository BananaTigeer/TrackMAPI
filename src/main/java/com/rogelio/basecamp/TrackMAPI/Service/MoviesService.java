package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Movies;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface MoviesService {
    abstract void createMovie(Movies movie);
    abstract List<Movies> getAllMovies();
    abstract Optional<Movies> getMovie(ObjectId movieId);
    abstract Movies putMovie(ObjectId movieId, Movies movie);
    abstract Movies patchMovie(ObjectId movieId, Movies movie);
    abstract void deleteMovie(ObjectId movieId);
    abstract void deleteAllMovies();
}
