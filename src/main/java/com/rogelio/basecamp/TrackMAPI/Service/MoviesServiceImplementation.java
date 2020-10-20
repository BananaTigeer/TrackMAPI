package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Movie;
import com.rogelio.basecamp.TrackMAPI.Repository.MoviesRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesServiceImplementation implements MoviesService{

    @Autowired
    private MoviesRepository moviesRepository;

    public MoviesServiceImplementation(){ }

    @Override
    public void createMovie(Movie movie){
        moviesRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies(){
        return moviesRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovie(ObjectId movieId){
        return moviesRepository.findById(movieId);
    }

    @Override
    public Movie putMovie(ObjectId movieId, Movie movie){
        movie.setMovieId(movieId);
        return moviesRepository.save(movie);
    }

    @Override
    public Movie patchMovie(ObjectId movieId, Movie movie){
        movie.setMovieId(movieId);
        return moviesRepository.save(movie);
    }

    @Override
    public void deleteMovie(ObjectId movieId){
        moviesRepository.deleteById(movieId);
    }

    @Override
    public void deleteAllMovies(){
        moviesRepository.deleteAll();
    }

}
