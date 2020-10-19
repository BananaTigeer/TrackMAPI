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

    /*private List<Movies> movies;*/

    @Autowired
    private MovieRepository movieRepository;

    public MoviesService(){
/*      this.movies = new ArrayList<>();
        this.movies.add(new Movies(1, "The GodFather", "CrimeDramaFilm", "Francis Ford Coppolla"));
        this.movies.add(new Movies(2, "Justice League", "SuperHeroComicMovie", "Jack Snyder"));*/
    }

    public void createMovie(Movies movie){
        movieRepository.save(movie);
    }

    public List<Movies> getAllMovies(){
        return movieRepository.findAll();
    }

    public void deleteAllMovies(){
        this.movieRepository.deleteAll();
    }

    /*public Movies getMovie(int movieId){
        for(Movies movies : this.movies){
            if(movies.getMovieId() == movieId){
                return movies;
            }
        }

        return null;
    }*/

}
