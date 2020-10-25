package com.rogelio.basecamp.TrackMAPI.movie;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.bson.types.ObjectId;
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

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    void createMovie() throws Exception{
        // Sets new Movie to add then mock it to see if it works?
        Movie mockMovie = new Movie("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");
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
    void getMovies() throws Exception {
        Movie movie1 = new Movie("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");
        Movie movie2 = new Movie("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");
        Movie movie3 = new Movie("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");

        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2dc");
        ObjectId objectId2 = new ObjectId("5f91658ec735df31bb0cf2dc");
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].movieId", is("5f91658ec735df31bb0cf2dc")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].movieName", is("The Godfather")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].movieDescription", is("Crime Drama Film")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].composer", is("Francis Ford Coppolla")));
    }

    @Test
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
    void deleteMovie() throws Exception{
        ObjectId objectid = new ObjectId("5f91658ec735df31bb0cf2dc");
        Mockito.when(moviesService.deleteMovie(ArgumentMatchers.any())).thenReturn("Successfully deleted movie");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/movies/{moviesId}", objectid.toHexString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully deleted movie"));
    }

    @Test
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