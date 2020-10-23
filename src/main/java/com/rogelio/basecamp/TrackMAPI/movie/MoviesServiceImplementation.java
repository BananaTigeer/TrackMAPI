package com.rogelio.basecamp.TrackMAPI.movie;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.MethodNotAllowedException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.ServiceUnavailableException;
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
    public List<Movie> getAllMovies() throws ServiceUnavailableException{
        return moviesRepository.findAll();
    }

    @Override
    public Movie getMovie(ObjectId movieId) throws RecordNotFoundException, BadRequestException {
        return moviesRepository.findById(movieId).orElseThrow(() -> new RecordNotFoundException("Can't find " + movieId.toHexString() + ". It does not exist"));
    }

    @Override
    public Movie updateMovie(ObjectId movieId, Movie movie) throws MethodNotAllowedException, BadRequestException, RecordNotFoundException{
        if(!moviesRepository.existsById(movieId)){
            throw new RecordNotFoundException("Can't find " + movieId.toHexString() + ". It does not exist");
        }

        movie.setMovieId(movieId);
        return moviesRepository.save(movie);
    }

    @Override
    public void deleteMovie(ObjectId movieId) throws RecordNotFoundException, MethodNotAllowedException, BadRequestException {
        moviesRepository.deleteById(movieId);
    }

    @Override
    public void deleteAllMovies(){
        moviesRepository.deleteAll();
    }

}
