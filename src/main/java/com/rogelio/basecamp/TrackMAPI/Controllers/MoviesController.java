package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.Movie;
import com.rogelio.basecamp.TrackMAPI.Service.MoviesService;
import com.rogelio.basecamp.TrackMAPI.errorhandlin.BadSyntaxException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @PostMapping("")
    public void createMovie(@RequestBody Movie movie){
        moviesService.createMovie(movie);
    }

    @GetMapping("")
    public List<Movie> getMovies(){
        return moviesService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public Movie getMovie(@Valid @PathVariable String movieId){
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(movieId)){
            throw new BadSyntaxException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        return moviesService.getMovie(objectId);
    }

    @PutMapping("/{movieId}")
    public Movie putMovie(@Valid @PathVariable String movieId, @RequestBody Movie movie) {
        if(!ObjectId.isValid(movieId)){
            throw new BadSyntaxException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        return moviesService.updateMovie(objectId, movie);
    }

    @PatchMapping("/{movieId}")
    public Movie patchMovie(@PathVariable ObjectId movieId, @RequestBody Movie movie){
        return moviesService.updateMovie(movieId, movie);
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
