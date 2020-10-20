package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.Movies;
import com.rogelio.basecamp.TrackMAPI.Service.MoviesServiceImplementation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesServiceImplementation moviesServiceImplementation;

    @PostMapping("")
    public void createMovie(@RequestBody Movies movie){
        moviesServiceImplementation.createMovie(movie);
    }

    @GetMapping("")
    public List<Movies> getMovies(){
        return moviesServiceImplementation.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public Optional<Movies> getMovie(@PathVariable ObjectId movieId){
        return moviesServiceImplementation.getMovie(movieId);
    }

    @PutMapping("/{movieId}")
    public Movies putMovie(@PathVariable ObjectId movieId, @RequestBody Movies movie){
        return moviesServiceImplementation.putMovie(movieId, movie);
    }

    @PatchMapping("/{movieId}")
    public Movies patchMovie(@PathVariable ObjectId movieId, @RequestBody Movies movie){
        return moviesServiceImplementation.patchMovie(movieId, movie);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable ObjectId movieId){
        moviesServiceImplementation.deleteMovie(movieId);
    }

    @DeleteMapping("")
    public void deleteAllMovies(){
        moviesServiceImplementation.deleteAllMovies();
    }


}
