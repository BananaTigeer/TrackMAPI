package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Movies;
import com.rogelio.basecamp.TrackMAPI.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteAllMovies(){
        this.movieRepository.deleteAll();
    }

}
