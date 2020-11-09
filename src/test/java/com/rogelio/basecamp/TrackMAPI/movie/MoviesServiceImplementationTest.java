package com.rogelio.basecamp.TrackMAPI.movie;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MoviesServiceImplementationTest {

    @Autowired
    private MoviesService moviesService;

    @MockBean
    private MoviesRepository moviesRepository;

    @Test
    @DisplayName("save movie success")
    void createMovie() {
        //Mocked movie and repository
        Movie mockMovie = new Movie("The GodFather", "Crime Drama Film", "Francis Ford Coppola");
        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);

        //Service
        Movie returnedMovie = moviesService.createMovie(mockMovie);

        //Testing
        Assertions.assertNotNull(returnedMovie, "Not null");
        Assertions.assertSame(returnedMovie, mockMovie, "same");
        Assertions.assertEquals(returnedMovie.getMovieName(), "The GodFather");
    }

    @Test
    @DisplayName("findAll movies success")
    void getAllMovies() {
        //Mocking retrieving collection of movies from repository
        Movie mockMovie1 = new Movie("The GodFather", "Crime Drama Film", "Francis Ford Coppola");
        Movie mockMovie2 = new Movie("The Good, The bad, and the Ugly", "Spaghetti Western Film", " Sergio Leone");
        Movie mockMovie3 = new Movie("Iron Man", "Marvel Comics Film", "Jon Favraeu");
        List<Movie> mockMovieCollection = new ArrayList<Movie>();
        mockMovieCollection.add(mockMovie1);
        mockMovieCollection.add(mockMovie2);
        mockMovieCollection.add(mockMovie3);
        Mockito.when(moviesRepository.findAll()).thenReturn(mockMovieCollection);

        //Service
        List<Movie> returnedMovieCollection = moviesService.getAllMovies();

        //Validate
        Assertions.assertFalse(returnedMovieCollection.isEmpty());
        Assertions.assertSame(returnedMovieCollection.get(0), mockMovie1);
        Assertions.assertEquals(returnedMovieCollection.get(1).getMovieName(), "The Good, The bad, and the Ugly");
    }

    @Test
    @DisplayName("findById movie success")
    void getMovieFound() {
        //Mocking retrieving a movie from repository and assuming it's found
        Movie mockMovie = new Movie("The GodFather", "Crime Drama Film", "Francis Ford Coppola");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockMovie.setMovieId(objectId);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));

        //Service
        Movie returnedMovie = moviesService.getMovie(mockMovie.getMovieId());

        Assertions.assertNotNull(returnedMovie);
        Assertions.assertSame(returnedMovie, mockMovie);
        Assertions.assertEquals(returnedMovie.getMovieName(), "The GodFather");
    }

    @Test()
    @DisplayName("findById movie invalid id exception success")
    void getMovieInvalidId(){
        //Mocking invalid id throwing exception
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            moviesService.getMovie("123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update movie invalid id exception success")
    void updateMovieInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            moviesService.updateMovie("123", new Movie("Test", "Test", "Test"));
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update movie success")
    void updateMovieValidIdButFound(){
        Movie mockMovie = new Movie("The GodFather", "Crime Drama Film", "Francis Ford Coppola");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("Test");
        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);

        Movie returnedMovie = moviesService.updateMovie(objectId.toHexString(), mockMovie);

        Assertions.assertSame(returnedMovie, mockMovie);
    }

    @Test
    @DisplayName("delete movie invalid id exception success")
    void deleteMovieInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            moviesService.deleteMovie("123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("delete movie exception success")
    void deleteMovieValidId() {
        moviesService.deleteMovie("5f91658ec735df31bb0cf2dc");
        assertEquals(moviesService.deleteMovie("5f91658ec735df31bb0cf2dc"), "Successfully deleted movie");
    }

    @Test
    @DisplayName("delete all movies success")
    void deleteAllMovies() {
        moviesService.deleteAllMovies();
        assertEquals(moviesService.deleteAllMovies(), "Successfully deleted all movies");
    }
}