package com.rogelio.basecamp.TrackMAPI.movie;

import com.rogelio.basecamp.TrackMAPI.errorhandling.*;
import com.rogelio.basecamp.TrackMAPI.user.User;
import com.rogelio.basecamp.TrackMAPI.user.UsersService;
import org.bson.types.ObjectId;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MoviesServiceImplementation implements MoviesService{

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private UsersService userService;


    @Override
    public Movie createMovie(Movie movie){
        return moviesRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies(){
        return moviesRepository.findAll();
    }

    @Override
    public Movie getMovie(HttpServletRequest request, String movieId){
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        //Convert movieId to type ObjectId
        ObjectId objectId = new ObjectId(movieId);


        if(!moviesRepository.existsById(objectId)){
            throw new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist");
        }

        //Retrieve user id from access token
        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String userId = principal.getAccount().getKeycloakSecurityContext().getToken().getSubject();

        //Store user id to database
        User user = new User();
        user.setUserId(userId);
        user.setMoviesWatched(1);
        userService.createUser(user);

        return moviesRepository.findById(objectId).orElseThrow(() -> new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist"));
    }

    @Override
    public Movie putMovie(String movieId, Movie movie){
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        movie.setMovieId(objectId);

        return moviesRepository.save(movie);
    }

    @Override
    public Movie patchMovie(String movieId, Movie movie){
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);
        Movie existingMovie = moviesRepository.findById(objectId).get();

        //region field updaters
        if(movie.getMovieName() != null){
            existingMovie.setMovieName(movie.getMovieName());
        }

        if(movie.getMovieDescription() != null){
            existingMovie.setMovieDescription(movie.getMovieDescription());
        }

        if(movie.getDirectedBy() != null){
            existingMovie.setDirectedBy(movie.getDirectedBy());
        }

        if(movie.getComposer() != null){
            existingMovie.setComposer(movie.getComposer());
        }

        if(movie.getDateReleased() != null){
            existingMovie.setDateReleased(movie.getDateReleased());
        }

        if(movie.getActors() != null){
            existingMovie.setActors(movie.getActors());
        }

        if(movie.getRunningTime() != null){
            existingMovie.setRunningTime(movie.getRunningTime());
        }

        if(movie.getProductionCompany() != null){
            existingMovie.setProductionCompany(movie.getProductionCompany());
        }

        if(movie.getDistributedBy() != null){
            existingMovie.setDistributedBy(movie.getDistributedBy());
        }

        if(movie.getCoverArtLink() != null){
            existingMovie.setCoverArtLink(movie.getCoverArtLink());
        }

        if(movie.getWriters() != null){
            existingMovie.setWriters(movie.getWriters());
        }

        if(movie.getGenre() != null){
            existingMovie.setGenre(movie.getGenre());
        }
        //endregion

        return moviesRepository.save(existingMovie);
    }

    @Override
    public String deleteMovie(String movieId){
        if(!ObjectId.isValid(movieId)){
            throw new BadRequestException("Invalid Id: " + movieId);
        }

        ObjectId objectId = new ObjectId(movieId);

        moviesRepository.deleteById(objectId);
        return "Successfully deleted movie";
    }

    @Override
    public String deleteAllMovies(){
        moviesRepository.deleteAll();
        return "Successfully deleted all movies";
    }

}
