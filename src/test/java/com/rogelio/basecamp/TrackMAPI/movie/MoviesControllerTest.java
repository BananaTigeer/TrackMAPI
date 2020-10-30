package com.rogelio.basecamp.TrackMAPI.movie;

import static org.hamcrest.Matchers.is;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
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
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockMovie.setMovieId(objectId);

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
        List<String> actors1 = new ArrayList<>();
        actors1.add("Marlon Brando");
        actors1.add("Al Pacino");
        List<String> productionCompany1 = new ArrayList<>();
        productionCompany1.add("Paramount Pictures");
        productionCompany1.add("Alftan Productions");
        List<String> writers1 = new ArrayList<>();
        writers1.add("Mario Puzo");
        writers1.add("Francis Ford Coppola");

        Movie movie1 = new Movie("The Godfather",
                "Crime Drama Film",
                "Francis Ford Coppola",
                "Francis Ford Coppola",
                "March 14, 1972",
                actors1,
                "177 minutes",
                productionCompany1,
                "Paramount Pictures",
                "https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg",
                writers1,
                "crime film");


        List<String> actors2 = new ArrayList<>();
        actors2.add("Marlon Brando");
        actors2.add("Al Pacino");
        List<String> productionCompany2 = new ArrayList<>();
        productionCompany2.add("Paramount Pictures");
        productionCompany2.add("Alftan Productions");
        List<String> writers2 = new ArrayList<>();
        writers2.add("Mario Puzo");
        writers2.add("Francis Ford Coppola");

        Movie movie2 = new Movie("The GoodFellas",
                "Crime movie description right here",
                "Nino Rota",
                "Francis Ford Coppola",
                "March 14, 1972",
                actors2,
                "177 minutes",
                productionCompany2,
                "Paramount Pictures",
                "https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg",
                writers2,
                "crime film");


        List<String> actors3 = new ArrayList<>();
        actors3.add("Marlon Brando");
        actors3.add("Al Pacino");
        List<String> productionCompany3 = new ArrayList<>();
        productionCompany3.add("Paramount Pictures");
        productionCompany3.add("Alftan Productions");
        List<String> writers3 = new ArrayList<>();
        writers3.add("Mario Puzo");
        writers3.add("Francis Ford Coppola");

        Movie movie3 = new Movie("The Good, the Bad, and the Ugly",
                "Crime movie description right here",
                "Nino Rota",
                "Francis Ford Coppola",
                "March 14, 1972",
                actors3,
                "177 minutes",
                productionCompany3,
                "Paramount Pictures",
                "https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg",
                writers3,
                "crime film");

        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2da");
        ObjectId objectId2 = new ObjectId("5f91658ec735df31bb0cf2db");
        ObjectId objectId3 = new ObjectId("5f91658ec735df31bb0cf2dc");

        movie1.setMovieId(objectId1);
        movie2.setMovieId(objectId2);
        movie3.setMovieId(objectId3);

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].movieName", is("The Godfather")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].movieDescription", is("Crime Drama Film")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].composer", is("Francis Ford Coppola")));
    }

    @Test
    @DisplayName("GET movie success")
    void getMovie() throws Exception{
        Movie movie1 = new Movie("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");
        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2dc");
        movie1.setMovieId(objectId1);

        doReturn(movie1).when(moviesService).getMovie(movie1.getMovieId());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/movies/{movieId}",objectId1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.movieName", is("The Godfather")));
    }

    @Test
    @DisplayName("PUT movie success")
    void putMovie() throws Exception{
        Movie mockMovie = new Movie("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("Test");
        mockMovie.setMovieDescription("");
        mockMovie.setComposer("");

        //doReturn(mockMovie).when(moviesService).updateMovie(objectId, mockMovie);
        Mockito.when(moviesService.updateMovie(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockMovie);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/movies/{movieId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockMovie))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.movieName", is("Test")))
                .andExpect(jsonPath("$.movieDescription", is("")));
    }

    @Test
    @DisplayName("PATCH movie success")
    void patchMovie() throws Exception{
        Movie mockMovie = new Movie("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockMovie.setMovieId(objectId);
        mockMovie.setMovieName("Test");

        Mockito.when(moviesService.updateMovie(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockMovie);

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/movies/{movieId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockMovie))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.movieName", is("Test")))
                .andExpect(jsonPath("$.movieDescription", is("Crime Drama Film")));
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