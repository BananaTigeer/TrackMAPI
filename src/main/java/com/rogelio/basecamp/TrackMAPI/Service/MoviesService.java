package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Movies;
import com.rogelio.basecamp.TrackMAPI.Repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {

    @Autowired
    private MovieRepository movieRepository;

    public MoviesService(){ }

    public void createMovie(Movies movie){
        movieRepository.save(movie);
    }

    public List<Movies> getAllMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movies> getMovie(ObjectId movieId){
        return movieRepository.findById(movieId);
    }

    public Movies putMovie(ObjectId movieId, Movies movie){
        movie.setMovieId(movieId);
        return movieRepository.save(movie);
    }

    public Movies patchMovie(ObjectId movieId, Movies movie){
        movie.setMovieId(movieId);
        return movieRepository.save(movie);
    }

    public void deleteMovie(ObjectId movieId){
        movieRepository.deleteById(movieId);
    }

    public void deleteAllMovies(){
        movieRepository.deleteAll();
    }

}
