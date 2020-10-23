package com.rogelio.basecamp.TrackMAPI.movie;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @PostMapping("")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie ){
        Movie createdMovie = moviesService.createMovie(movie);

        try{
            return ResponseEntity.created(new URI("/movies"))
                    .body(createdMovie);
        }catch(URISyntaxException e){
            throw new RuntimeException("Error in POST /movies");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Movie>> getMovies(){
        return ResponseEntity.ok().body(moviesService.getAllMovies());
    }

    @GetMapping("/{movieId}")
    public ResponseEntity getMovie(@Valid @RequestBody @PathVariable String movieId){
        return ResponseEntity.ok().body(moviesService.getMovie(movieId));
    }

    @PutMapping("/{movieId}")
    public ResponseEntity putMovie(@Valid @PathVariable String movieId, @RequestBody Movie movie){
        return ResponseEntity.ok().body(moviesService.updateMovie(movieId, movie));
    }

    @PatchMapping("/{movieId}")
    public ResponseEntity patchMovie(@Valid @PathVariable String movieId, @RequestBody Movie movie){
        return ResponseEntity.ok().body(moviesService.updateMovie(movieId, movie));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity deleteMovie(@Valid @PathVariable String movieId){
        return ResponseEntity.ok().body(moviesService.deleteMovie(movieId));
    }

    @DeleteMapping("")
    public ResponseEntity deleteAllMovies(){
        return ResponseEntity.ok().body(moviesService.deleteAllMovies());
    }

}
