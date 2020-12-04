package com.rogelio.basecamp.TrackMAPI.movie;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.doReturn;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class MoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoviesService moviesService;

    @Test
    @DisplayName("POST movie success")
    void createMovie() throws Exception{
        // Sets new Movie to add then mock it to see if it works?
        List<String> actors = new ArrayList<>();
        actors.add("Marlon Brando");
        actors.add("Al Pacino");
        List<String> productionCompany = new ArrayList<>();
        productionCompany.add("Paramount Pictures");
        productionCompany.add("Alftan Productions");
        List<String> writers = new ArrayList<>();
        writers.add("Mario Puzo");
        writers.add("Francis Ford Coppola");

        /*
        Movie mockMovie = new Movie("The GoodFellas",
                "Crime movie description right here",
                "Nino Rota",
                "Francis Ford Coppolla",
                "March 14, 1972",
                actors,
                "177 minutes",
                productionCompany,
                "Paramount Pictures",
                "https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg",
                writers,
                "crime film");
         */

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

        Mockito.when(moviesService.createMovie(ArgumentMatchers.any())).thenReturn(mockMovie);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/movies")
                .content(asJsonString(mockMovie))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.movieId", is("5f91658ec735df31bb0cf2dc")));
    }


    @Test
    @DisplayName("GET all movies success")
    void getMovies() throws Exception {
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
        movie2.setMovieName("The GoodFellas");
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
        movie3.setMovieName("The GoodFellas");
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

        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);

        doReturn(movies).when(moviesService).getAllMovies();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].movieId", is("5f91658ec735df31bb0cf2da")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].movieName", is("The GoodFellas")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].movieDescription", is("Crime movie description right here")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].composer", is("Francis Ford Coppolla")));
    }

    @Test
    @DisplayName("GET movie success")
    void getMovie() throws Exception{
        List<String> actors = new ArrayList<>();
        actors.add("Marlon Brando");
        actors.add("Al Pacino");
        List<String> productionCompany = new ArrayList<>();
        productionCompany.add("Paramount Pictures");
        productionCompany.add("Alftan Productions");
        List<String> writers = new ArrayList<>();
        writers.add("Mario Puzo");
        writers.add("Francis Ford Coppola");

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

        doReturn(mockMovie).when(moviesService).getMovie(ArgumentMatchers.any(), ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/movies/{movieId}",objectId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.movieName", is("The GoodFellas")));
    }

    @Test
    @DisplayName("PUT movie success")
    void putMovie() throws Exception{
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("Test");

        //doReturn(mockMovie).when(moviesService).updateMovie(objectId, mockMovie);
        Mockito.when(moviesService.putMovie(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockMovie);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/movies/{movieId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockMovie))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.movieName", is("Test")))
                .andExpect(jsonPath("$.movieDescription").doesNotExist());
    }

    @Test
    @DisplayName("PATCH movie success")
    void patchMovie() throws Exception{
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("Test");

        Mockito.when(moviesService.patchMovie(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockMovie);

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/movies/{movieId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockMovie))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.movieName", is("Test")));
    }

    @Test
    @DisplayName("DELETE movie success")
    void deleteMovie() throws Exception{
        ObjectId objectid = new ObjectId("5f91658ec735df31bb0cf2dc");
        Mockito.when(moviesService.deleteMovie(ArgumentMatchers.any())).thenReturn("Successfully deleted movie");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/movies/{moviesId}", objectid.toHexString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully deleted movie"));
    }

    @Test
    @DisplayName("DELETE all movies success")
    void deleteAllMovies() throws Exception{
        Mockito.when(moviesService.deleteAllMovies()).thenReturn("Successfully deleted all movies");

        mockMvc.perform(MockMvcRequestBuilders
        .delete("/movies"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully deleted all movies"));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}