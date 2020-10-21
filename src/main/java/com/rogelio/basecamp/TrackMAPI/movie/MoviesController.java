package com.rogelio.basecamp.TrackMAPI.movie;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
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
    public void createMovie(@Valid @RequestBody Movie movie){
        moviesService.createMovie(movie);
    }

    @GetMapping("")
    public List<Movie> getMovies(){
        return moviesService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public Movie getMovie(@Valid @RequestBody @PathVariable String movieId){
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        return moviesService.getMovie(objectId);
    }

    @PutMapping("/{movieId}")
    public Movie putMovie(@Valid @PathVariable String movieId, @RequestBody Movie movie) {
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        return moviesService.updateMovie(objectId, movie);
    }

    @PatchMapping("/{movieId}")
    public Movie patchMovie(@Valid @PathVariable ObjectId movieId, @RequestBody Movie movie){
        return moviesService.updateMovie(movieId, movie);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@Valid @PathVariable ObjectId movieId){
        moviesService.deleteMovie(movieId);
    }

    @DeleteMapping("")
    public void deleteAllMovies(){
        moviesService.deleteAllMovies();
    }


}
