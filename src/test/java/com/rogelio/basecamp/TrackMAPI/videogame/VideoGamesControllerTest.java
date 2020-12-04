package com.rogelio.basecamp.TrackMAPI.videogame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogelio.basecamp.TrackMAPI.movie.Movie;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class VideoGamesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoGamesService videoGamesService;

    @Test
    @DisplayName("POST video-games success")
    void createVideoGame() throws Exception{
        // Sets new video game to add then mock it to see if it works?
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameName("Halo Infinite");
        mockVideoGame.setGameDescription("First Person Shooter");
        mockVideoGame.setPublisher("Microsoft Game Studios");

        mockVideoGame.setGameId(objectId);

        Mockito.when(videoGamesService.createVideoGame(ArgumentMatchers.any())).thenReturn(mockVideoGame);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/video-games")
                .content(asJsonString(mockVideoGame))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.gameId", is("5f91658ec735df31bb0cf2dc")));
    }

    @Test
    @DisplayName("GET all video games success")
    void getAllVideoGames() throws Exception{
        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2da");
        ObjectId objectId2 = new ObjectId("5f91658ec735df31bb0cf2db");
        ObjectId objectId3 = new ObjectId("5f91658ec735df31bb0cf2dc");

        VideoGame videoGame1 = new VideoGame();
        videoGame1.setGameId(objectId1);
        videoGame1.setGameName("Halo CE");
        videoGame1.setGameDescription("First Person Shooter");
        videoGame1.setPublisher("Microsoft Game Studios");

        VideoGame videoGame2 = new VideoGame();
        videoGame2.setGameId(objectId2);
        videoGame2.setGameName("Halo 2");
        videoGame2.setGameDescription("First Person Shooter");
        videoGame2.setPublisher("Microsoft Game Studios");

        VideoGame videoGame3 = new VideoGame();
        videoGame3.setGameId(objectId3);
        videoGame3.setGameName("Halo 3");
        videoGame3.setGameDescription("First Person Shooter");
        videoGame3.setPublisher("Microsoft Game Studios");

        List<VideoGame> videoGames = new ArrayList<>();
        videoGames.add(videoGame1);
        videoGames.add(videoGame2);
        videoGames.add(videoGame3);

        doReturn(videoGames).when(videoGamesService).getAllVideoGames();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/video-games"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].gameId", is("5f91658ec735df31bb0cf2da")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].gameName", is("Halo CE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].gameDescription", is("First Person Shooter")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].publisher", is("Microsoft Game Studios")));
    }

    @Test
    @DisplayName("GET video-game success")
    void getVideoGame() throws Exception{
        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId1);
        mockVideoGame.setGameName("Halo CE");
        mockVideoGame.setGameDescription("First Person Shooter");
        mockVideoGame.setPublisher("Microsoft Game Studios");

        doReturn(mockVideoGame).when(videoGamesService).getVideoGame(ArgumentMatchers.any(), ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/video-games/{gameId}",objectId1.toHexString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.gameName", is("Halo CE")));
    }

    @Test
    @DisplayName("PUT video-game success")
    void putVideoGame() throws Exception{
        VideoGame mockVideoGame = new VideoGame();

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameName("Halo CE: Anniversary");
        mockVideoGame.setGameDescription("");
        mockVideoGame.setPublisher("");

        //doReturn(mockMovie).when(moviesService).updateMovie(objectId, mockMovie);
        Mockito.when(videoGamesService.putVideoGame(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockVideoGame);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/video-games/{gameId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockVideoGame))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.gameName", is("Halo CE: Anniversary")))
                .andExpect(jsonPath("$.gameDescription", is("")));
    }

    @Test
    @DisplayName("PATCH video-game success")
    void patchVideoGame() throws Exception{
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameName("Halo CE: Anniversary");
        mockVideoGame.setGameDescription("First Person Shooter");
        mockVideoGame.setPublisher("Microsoft Game Studios");

        Mockito.when(videoGamesService.patchVideoGame(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockVideoGame);

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/video-games/{gameId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockVideoGame))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.gameName", is("Halo CE: Anniversary")))
                .andExpect(jsonPath("$.gameDescription", is("First Person Shooter")));
    }


    @Test
    @DisplayName("DELETE video-game success")
    void deleteVideoGame() throws Exception{
        ObjectId objectid = new ObjectId("5f91658ec735df31bb0cf2dc");
        Mockito.when(videoGamesService.deleteVideoGame(ArgumentMatchers.any())).thenReturn("Successfully deleted video game");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/video-games/{gameId}", objectid.toHexString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully deleted video game"));
    }

    @Test
    @DisplayName("DELETE all video-game success")
    void deleteAllVideoGames() throws Exception{
        Mockito.when(videoGamesService.deleteAllVideoGames()).thenReturn("Successfully deleted all video games");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/video-games"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully deleted all video games"));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}