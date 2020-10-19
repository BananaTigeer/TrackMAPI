package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.Movies;
import com.rogelio.basecamp.TrackMAPI.Service.MoviesService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @PostMapping("")
    public void createMovie(@RequestBody Movies movie){
        moviesService.createMovie(movie);
    }

    @GetMapping("")
    public List<Movies> getMovies(){
        return moviesService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public Optional<Movies> getMovie(@PathVariable ObjectId movieId){
        return moviesService.getMovie(movieId);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable ObjectId movieId){
        moviesService.deleteMovie(movieId);
    }

    @DeleteMapping("")
    public void deleteAllMovies(){
        moviesService.deleteAllMovies();
    }


}
