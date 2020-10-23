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

/*  @PostMapping("")
    @ResponseBody
    public Movie createMovie(@Valid @RequestBody Movie movie){
        moviesService.createMovie(movie);
        return movie;
    }*/

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


/*    @GetMapping("")
    public List<Movie> getMovies(){
        return moviesService.getAllMovies();
    }*/

    @GetMapping("")
    public ResponseEntity<List<Movie>> getMovies(){
        return ResponseEntity.ok().body(moviesService.getAllMovies());
    }

/*    @GetMapping("/{movieId}")
    public Movie getMovie(@Valid @RequestBody @PathVariable String movieId){
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        return moviesService.getMovie(objectId);
    }*/

    @GetMapping("/{movieId}")
    public ResponseEntity getMovie(@Valid @RequestBody @PathVariable String movieId){
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        return ResponseEntity.ok().body(moviesService.getMovie(objectId));
    }

/*    @PutMapping("/{movieId}")
    public Movie putMovie(@Valid @PathVariable String movieId, @RequestBody Movie movie) {
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        return moviesService.updateMovie(objectId, movie);
    }*/

    @PutMapping("/{movieId}")
    public ResponseEntity putMovie(@Valid @PathVariable String movieId, @RequestBody Movie movie){
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        return ResponseEntity.ok().body(moviesService.updateMovie(objectId, movie));
    }

/*    @PatchMapping("/{movieId}")
    public Movie patchMovie(@Valid @PathVariable String movieId, @RequestBody Movie movie){
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        return moviesService.updateMovie(objectId, movie);
    }*/

    @PatchMapping("/{movieId}")
    public ResponseEntity patchMovie(@Valid @PathVariable String movieId, @RequestBody Movie movie){
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        return ResponseEntity.ok().body(moviesService.updateMovie(objectId, movie));
    }

/*    @DeleteMapping("/{movieId}")
    public void deleteMovie(@Valid @PathVariable String movieId){
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        moviesService.deleteMovie(objectId);
    }*/

    @DeleteMapping("/{movieId}")
    public ResponseEntity deleteMovie(@Valid @PathVariable String movieId){
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        moviesService.deleteMovie(objectId);
        return ResponseEntity.ok().body("Movie successfully deleted");
    }

/*    @DeleteMapping("")
    public void deleteAllMovies(){
        moviesService.deleteAllMovies();
    }*/

    @DeleteMapping("")
    public ResponseEntity deleteAllMovies(){
        moviesService.deleteAllMovies();
        return ResponseEntity.ok().body("Successfully deleted all movies");
    }

}
