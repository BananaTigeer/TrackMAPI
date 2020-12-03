package com.rogelio.basecamp.TrackMAPI.movie;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
import com.rogelio.basecamp.TrackMAPI.videogame.VideoGame;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class MoviesServiceImplementationTest {

    @Autowired
    private MoviesService moviesService;

    @MockBean
    private MoviesRepository moviesRepository;

    @Test
    @DisplayName("save movie success")
    void createMovie() {
        List<String> actors = new ArrayList<>();
        actors.add("Marlon Brando");
        actors.add("Al Pacino");
        List<String> productionCompany = new ArrayList<>();
        productionCompany.add("Paramount Pictures");
        productionCompany.add("Alftan Productions");
        List<String> writers = new ArrayList<>();
        writers.add("Mario Puzo");
        writers.add("Francis Ford Coppola");

        //Mocked movie and repository
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("The GoodFellas");
        mockMovie.setMovieDescription("Crime movie description right here");
        mockMovie.setDirectedBy("Nino Rota");
        mockMovie.setComposer("Francis Ford Coppolla");
        mockMovie.setDateReleased("March 14, 1972");
        mockMovie.setActors(actors);
        mockMovie.setRunningTime("177 minutes");
        mockMovie.setProductionCompany(productionCompany);
        mockMovie.setDistributedBy("Paramount Pictures");
        mockMovie.setCoverArtLink("https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg");
        mockMovie.setWriters(writers);
        mockMovie.setGenre("crime film");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);

        //Service
        Movie returnedMovie = moviesService.createMovie(mockMovie);

        //Testing
        Assertions.assertNotNull(returnedMovie, "Not null");
        Assertions.assertSame(returnedMovie, mockMovie, "same");
        Assertions.assertEquals(returnedMovie.getMovieName(), "The GoodFellas");
    }

    @Test
    @DisplayName("findAll movies success")
    void getAllMovies() {
        //arrays for movie 1
        List<String> actors1 = new ArrayList<>();
        actors1.add("Marlon Brando");
        actors1.add("Al Pacino");
        List<String> productionCompany1 = new ArrayList<>();
        productionCompany1.add("Paramount Pictures");
        productionCompany1.add("Alftan Productions");
        List<String> writers1 = new ArrayList<>();
        writers1.add("Mario Puzo");
        writers1.add("Francis Ford Coppola");

        //arrays for movie 2
        List<String> actors2 = new ArrayList<>();
        actors2.add("Marlon Brando");
        actors2.add("Al Pacino");
        List<String> productionCompany2 = new ArrayList<>();
        productionCompany2.add("Paramount Pictures");
        productionCompany2.add("Alftan Productions");
        List<String> writers2 = new ArrayList<>();
        writers2.add("Mario Puzo");
        writers2.add("Francis Ford Coppola");

        //arrays for movie 3
        List<String> actors3 = new ArrayList<>();
        actors3.add("Marlon Brando");
        actors3.add("Al Pacino");
        List<String> productionCompany3 = new ArrayList<>();
        productionCompany3.add("Paramount Pictures");
        productionCompany3.add("Alftan Productions");
        List<String> writers3 = new ArrayList<>();
        writers3.add("Mario Puzo");
        writers3.add("Francis Ford Coppola");

        //ids for movies
        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2da");
        ObjectId objectId2 = new ObjectId("5f91658ec735df31bb0cf2db");
        ObjectId objectId3 = new ObjectId("5f91658ec735df31bb0cf2dc");

        //mock movie 1
        Movie movie1 = new Movie();
        movie1.setMovieId(objectId1);
        movie1.setMovieName("The GoodFellas");
        movie1.setMovieDescription("Crime movie description right here");
        movie1.setDirectedBy("Nino Rota");
        movie1.setComposer("Francis Ford Coppolla");
        movie1.setDateReleased("March 14, 1972");
        movie1.setActors(actors1);
        movie1.setRunningTime("177 minutes");
        movie1.setProductionCompany(productionCompany1);
        movie1.setDistributedBy("Paramount Pictures");
        movie1.setCoverArtLink("https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg");
        movie1.setWriters(writers1);
        movie1.setGenre("crime film");

        //mock movie 2
        Movie movie2 = new Movie();
        movie2.setMovieId(objectId2);
        movie2.setMovieName("The Good, The Bad, and The Ugly");
        movie2.setMovieDescription("Crime movie description right here");
        movie2.setDirectedBy("Nino Rota");
        movie2.setComposer("Francis Ford Coppolla");
        movie2.setDateReleased("March 14, 1972");
        movie2.setActors(actors1);
        movie2.setRunningTime("177 minutes");
        movie2.setProductionCompany(productionCompany2);
        movie2.setDistributedBy("Paramount Pictures");
        movie2.setCoverArtLink("https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg");
        movie2.setWriters(writers2);
        movie2.setGenre("crime film");

        //mock movie 3
        Movie movie3 = new Movie();
        movie3.setMovieId(objectId3);
        movie3.setMovieName("The Godfather");
        movie3.setMovieDescription("Crime movie description right here");
        movie3.setDirectedBy("Nino Rota");
        movie3.setComposer("Francis Ford Coppolla");
        movie3.setDateReleased("March 14, 1972");
        movie3.setActors(actors3);
        movie3.setRunningTime("177 minutes");
        movie3.setProductionCompany(productionCompany3);
        movie3.setDistributedBy("Paramount Pictures");
        movie3.setCoverArtLink("https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg");
        movie3.setWriters(writers3);
        movie3.setGenre("crime film");

        List<Movie> mockMovieCollection = new ArrayList<>();
        mockMovieCollection.add(movie1);
        mockMovieCollection.add(movie2);
        mockMovieCollection.add(movie3);

        Mockito.when(moviesRepository.findAll()).thenReturn(mockMovieCollection);

        //Service
        List<Movie> returnedMovieCollection = moviesService.getAllMovies();

        //Validate
        Assertions.assertFalse(returnedMovieCollection.isEmpty());
        Assertions.assertSame(returnedMovieCollection.get(0), movie1);
        Assertions.assertSame(returnedMovieCollection.get(1), movie2);
        Assertions.assertSame(returnedMovieCollection.get(2), movie3);
    }


    @Disabled
    @Test
    @DisplayName("findById movie success")
    void getMovieFound() {
        List<String> actors = new ArrayList<>();
        actors.add("Marlon Brando");
        actors.add("Al Pacino");
        List<String> productionCompany = new ArrayList<>();
        productionCompany.add("Paramount Pictures");
        productionCompany.add("Alftan Productions");
        List<String> writers = new ArrayList<>();
        writers.add("Mario Puzo");
        writers.add("Francis Ford Coppola");

        //Mocked movie and repository
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("The GoodFellas");
        mockMovie.setMovieDescription("Crime movie description right here");
        mockMovie.setDirectedBy("Nino Rota");
        mockMovie.setComposer("Francis Ford Coppolla");
        mockMovie.setDateReleased("March 14, 1972");
        mockMovie.setActors(actors);
        mockMovie.setRunningTime("177 minutes");
        mockMovie.setProductionCompany(productionCompany);
        mockMovie.setDistributedBy("Paramount Pictures");
        mockMovie.setCoverArtLink("https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg");
        mockMovie.setWriters(writers);
        mockMovie.setGenre("crime film");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        HttpServletRequest mockReq = Mockito.mock(HttpServletRequest.class);

        //Service
        Movie returnedMovie = moviesService.getMovie(mockReq, mockMovie.getMovieId());

        Assertions.assertNotNull(returnedMovie);
        Assertions.assertSame(returnedMovie, mockMovie);
    }

    @Test()
    @DisplayName("getMovie() movie does not exist throws RecordNotFound exception")
    void getMovieNotFound(){
        //Mocking game not found, throwing exception
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class, () -> {
            moviesService.getMovie(mockRequest, "5f91658ec735df31bb0cf2dc");
        });

        assertEquals("Can't find 5f91658ec735df31bb0cf2dc. It does not exist", recordNotFoundException.getMessage());
    }

    @Test()
    @DisplayName("findById movie invalid id exception success")
    void getMovieInvalidId(){
        //Mocking invalid id throwing exception
        MockHttpServletRequest request = new MockHttpServletRequest();
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            moviesService.getMovie(request, "123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update movie invalid id exception success")
    void putMovieInvalidId() {
        List<String> actors = new ArrayList<>();
        actors.add("Marlon Brando");
        actors.add("Al Pacino");
        List<String> productionCompany = new ArrayList<>();
        productionCompany.add("Paramount Pictures");
        productionCompany.add("Alftan Productions");
        List<String> writers = new ArrayList<>();
        writers.add("Mario Puzo");
        writers.add("Francis Ford Coppola");

        //Mocked movie and repository
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("The GoodFellas");
        mockMovie.setMovieDescription("Crime movie description right here");
        mockMovie.setDirectedBy("Nino Rota");
        mockMovie.setComposer("Francis Ford Coppolla");
        mockMovie.setDateReleased("March 14, 1972");
        mockMovie.setActors(actors);
        mockMovie.setRunningTime("177 minutes");
        mockMovie.setProductionCompany(productionCompany);
        mockMovie.setDistributedBy("Paramount Pictures");
        mockMovie.setCoverArtLink("https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg");
        mockMovie.setWriters(writers);
        mockMovie.setGenre("crime film");

        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            moviesService.putMovie("123", mockMovie);
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }


    @Test
    @DisplayName("update movie success")
    void putMovieValidIdButFound(){
        List<String> actors = new ArrayList<>();
        actors.add("Marlon Brando");
        actors.add("Al Pacino");
        List<String> productionCompany = new ArrayList<>();
        productionCompany.add("Paramount Pictures");
        productionCompany.add("Alftan Productions");
        List<String> writers = new ArrayList<>();
        writers.add("Mario Puzo");
        writers.add("Francis Ford Coppola");

        //Mocked movie and repository
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("The GoodFellas");
        mockMovie.setMovieDescription("Crime movie description right here");
        mockMovie.setDirectedBy("Nino Rota");
        mockMovie.setComposer("Francis Ford Coppolla");
        mockMovie.setDateReleased("March 14, 1972");
        mockMovie.setActors(actors);
        mockMovie.setRunningTime("177 minutes");
        mockMovie.setProductionCompany(productionCompany);
        mockMovie.setDistributedBy("Paramount Pictures");
        mockMovie.setCoverArtLink("https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg");
        mockMovie.setWriters(writers);
        mockMovie.setGenre("crime film");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Movie returnedMovie = moviesService.putMovie(objectId.toHexString(), mockMovie);
        Assertions.assertSame(returnedMovie, mockMovie);
    }

    @Test
    @DisplayName("patchMovie() valid id format")
    void patchVgValidId() {
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("Star Wars: Rise of Skywalker");
        mockMovie.setMovieDescription("Another star wars movie");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));

        //Service
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        //Testing
        Assertions.assertNotNull(moviesService, "Not null");
        Assertions.assertSame(returnedMovie, mockMovie, "same");
    }

    @Test
    @DisplayName("patchMovie() invalid id format throw BadRequest exception")
    void patchVgInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            moviesService.patchMovie("123", new Movie());
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    //region patchMovie() conditional branches
    @Test
    @DisplayName("patchMovie() getName is not null")
    void movieNameIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("Helen of Troy");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getMovieName());
    }

    @Test
    @DisplayName("patchMovie() getName is null")
    void movieNameIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getMovieName());
    }

    @Test
    @DisplayName("patchMovie() getDescription is not null")
    void movieDescriptionIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieDescription("Hector vs Achilles fight");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getMovieDescription());
    }

    @Test
    @DisplayName("patchMovie() getDescription is null")
    void movieDescriptionIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getMovieDescription());
    }

    @Test
    @DisplayName("patchMovie() getDirectedBy is not null")
    void directedByIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setDirectedBy("Jon Favreau");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getDirectedBy());
    }

    @Test
    @DisplayName("patchMovie() getDirectedBy is null")
    void directedByIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getDirectedBy());
    }

    @Test
    @DisplayName("patchMovie() getComposer is not null")
    void composerIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setComposer("John Williams");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getComposer());
    }

    @Test
    @DisplayName("patchMovie() getComposer is null")
    void composerByIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getComposer());
    }

    @Test
    @DisplayName("patchMovie() getDateReleased is not null")
    void dateReleasedIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setDateReleased("November 1, 2020");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getDateReleased());
    }

    @Test
    @DisplayName("patchMovie() getDateReleased is null")
    void dateReleasedIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getDateReleased());
    }

    @Test
    @DisplayName("patchMovie() getActors is not null")
    void actorsIsNotNull(){
        List<String> actors = new ArrayList<>();
        actors.add("Marlon Brando");
        actors.add("Al Pacino");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setActors(actors);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getActors());
    }

    @Test
    @DisplayName("patchMovie() getActors is null")
    void actorsIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getActors());
    }

    @Test
    @DisplayName("patchMovie() getRunningTime is not null")
    void runningTimeIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setRunningTime("120 minutes");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getRunningTime());
    }

    @Test
    @DisplayName("patchMovie() getRunningTime is null")
    void runningTimeIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getRunningTime());
    }

    @Test
    @DisplayName("patchMovie() getProductionCompany is not null")
    void productionCompanyIsNotNull(){
        List<String> prodComp = new ArrayList<>();
        prodComp.add("20th Century Fox");
        prodComp.add("Bad Robot");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setProductionCompany(prodComp);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getProductionCompany());
    }

    @Test
    @DisplayName("patchMovie() getProductionCompany is null")
    void productionCompanyIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getProductionCompany());
    }

    @Test
    @DisplayName("patchMovie() getDistributedBy is not null")
    void distributedByNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setDistributedBy("Warner Brothers");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getDistributedBy());
    }

    @Test
    @DisplayName("patchMovie() getDistributedBy is null")
    void distributedByIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getDistributedBy());
    }

    @Test
    @DisplayName("patchMovie() getCoverArtLink is not null")
    void coverArtLinkNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setCoverArtLink("https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getCoverArtLink());
    }

    @Test
    @DisplayName("patchMovie() getCoverArtLink is null")
    void coverArtLinkIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getCoverArtLink());
    }

    @Test
    @DisplayName("patchMovie() getWriters is not null")
    void writersNotNull(){
        List<String> writers = new ArrayList<>();
        writers.add("George R. R. Martin");
        writers.add("Mizuki Unohana");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setWriters(writers);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getWriters());
    }

    @Test
    @DisplayName("patchMovie() getWriters is null")
    void writersIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getWriters());
    }

    @Test
    @DisplayName("patchMovie() getGenre is not null")
    void genreNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setGenre("Sci-fi");

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNotNull(returnedMovie.getGenre());
    }

    @Test
    @DisplayName("patchMovie() getGenre is null")
    void genreIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);

        Mockito.when(moviesRepository.save(mockMovie)).thenReturn(mockMovie);
        Mockito.when(moviesRepository.findById(objectId)).thenReturn(Optional.of(mockMovie));
        Movie returnedMovie = moviesService.patchMovie(mockMovie.getMovieId(), mockMovie);

        Assertions.assertNull(returnedMovie.getGenre());
    }
    //endregion

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