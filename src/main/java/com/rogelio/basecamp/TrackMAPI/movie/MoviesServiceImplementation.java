package com.rogelio.basecamp.TrackMAPI.movie;

import com.rogelio.basecamp.TrackMAPI.errorhandling.*;
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
    public Movie createMovie(Movie movie){
        return moviesRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies(){
        return moviesRepository.findAll();
    }

    @Override
    public Movie getMovie(String movieId){
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);

        return moviesRepository.findById(objectId).orElseThrow(() -> new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist"));
    }

    @Override
    public Movie updateMovie(String movieId, Movie movie){
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);

        if(!moviesRepository.existsById(objectId)){
            throw new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist");
        }

        movie.setMovieId(objectId);
        return moviesRepository.save(movie);
    }

    @Override
    public String deleteMovie(String movieId){
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);

        if(!moviesRepository.existsById(objectId)){
            throw new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist");
        }

        moviesRepository.deleteById(objectId);
        return "Successfully deleted movie";
    }

    @Override
    public String deleteAllMovies(){
        if(moviesRepository.findAll().isEmpty()){
            throw new NoContentException("The server successfully processed the request, but is not returning any content");
        }

        moviesRepository.deleteAll();
        return "Successfully deleted all movies";
    }

}
