package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Movie;
import com.rogelio.basecamp.TrackMAPI.Repository.MoviesRepository;
import com.rogelio.basecamp.TrackMAPI.errorhandlin.RecordNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Movie getMovie(ObjectId movieId){
        return moviesRepository.findById(movieId).orElseThrow(() -> new RecordNotFoundException("Can't find " + movieId.toHexString() + ". It does not exist"));
    }

    @Override
    public Movie updateMovie(ObjectId movieId, Movie movie){
        if(!moviesRepository.existsById(movieId)){
            throw new RecordNotFoundException("Can't find " + movieId.toHexString() + ". It does not exist");
        }

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
