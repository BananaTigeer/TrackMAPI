package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Movies;
import com.rogelio.basecamp.TrackMAPI.Repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesServiceImplementation implements MoviesService{

    @Autowired
    private MovieRepository movieRepository;

    public MoviesServiceImplementation(){ }

    @Override
    public void createMovie(Movies movie){
        movieRepository.save(movie);
    }

    @Override
    public List<Movies> getAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movies> getMovie(ObjectId movieId){
        return movieRepository.findById(movieId);
    }

    @Override
    public Movies putMovie(ObjectId movieId, Movies movie){
        movie.setMovieId(movieId);
        return movieRepository.save(movie);
    }

    @Override
    public Movies patchMovie(ObjectId movieId, Movies movie){
        movie.setMovieId(movieId);
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(ObjectId movieId){
        movieRepository.deleteById(movieId);
    }

    @Override
    public void deleteAllMovies(){
        movieRepository.deleteAll();
    }

}
