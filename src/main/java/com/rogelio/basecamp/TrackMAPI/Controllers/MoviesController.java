package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.Games;
import com.rogelio.basecamp.TrackMAPI.Models.Movies;
import com.rogelio.basecamp.TrackMAPI.Service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("")
    public void deleteAllMovies(){
        moviesService.deleteAllMovies();
    }

/*    @GetMapping("")
    public List<Movies> getMovies(){
        return moviesService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public Movies getMovie(@PathVariable int movieId){
        return moviesService.getMovie(movieId);
    }*/

}
