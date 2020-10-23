package com.rogelio.basecamp.TrackMAPI.videogame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogelio.basecamp.TrackMAPI.movie.Movie;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class VideoGamesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoGamesService videoGamesService;

    @Test
    void createVideoGame() throws Exception{
        // Sets new video game to add then mock it to see if it works?
        VideoGame mockVideoGame = new VideoGame("Halo Infinite", "First Person Shooter", "Microsoft Game Studios");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
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
    void getAllVideoGames() throws Exception{
        VideoGame videoGame1 = new VideoGame("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");
        VideoGame videoGame2 = new VideoGame("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");
        VideoGame videoGame3 = new VideoGame("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");

        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2dc");
        ObjectId objectId2 = new ObjectId("5f91658ec735df31bb0cf2dc");
        ObjectId objectId3 = new ObjectId("5f91658ec735df31bb0cf2dc");

        videoGame1.setGameId(objectId1);
        videoGame2.setGameId(objectId2);
        videoGame3.setGameId(objectId3);

        List<VideoGame> videoGames = new ArrayList<>();
        videoGames.add(videoGame1);
        videoGames.add(videoGame2);
        videoGames.add(videoGame3);

        doReturn(videoGames).when(videoGamesService).getAllVideoGames();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/video-games"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].gameId", is("5f91658ec735df31bb0cf2dc")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].gameName", is("The Godfather")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].gameDescription", is("Crime Drama Film")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].publisher", is("Francis Ford Coppolla")));
    }

    @Test
    void getVideoGame() throws Exception{
        VideoGame mockVideoGame = new VideoGame("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");
        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockVideoGame.setGameId(objectId1);

        doReturn(mockVideoGame).when(videoGamesService).getVideoGame(mockVideoGame.getGameId());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/video-games/{gameId}",objectId1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.gameName", is("The Godfather")));
    }

    @Test
    void putVideoGame() throws Exception{
        VideoGame mockVideoGame = new VideoGame("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameName("Test");
        mockVideoGame.setGameDescription("");
        mockVideoGame.setPublisher("");

        //doReturn(mockMovie).when(moviesService).updateMovie(objectId, mockMovie);
        Mockito.when(videoGamesService.updateVideoGame(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockVideoGame);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/video-games/{gameId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockVideoGame))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.gameName", is("Test")))
                .andExpect(jsonPath("$.gameDescription", is("")));
    }

    @Test
    void patchVideoGame() throws Exception{
        VideoGame mockVideoGame = new VideoGame("The Godfather", "Crime Drama Film", "Francis Ford Coppolla");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameName("Test");

        Mockito.when(videoGamesService.updateVideoGame(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockVideoGame);

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/video-games/{gameId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockVideoGame))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.gameName", is("Test")))
                .andExpect(jsonPath("$.gameDescription", is("Crime Drama Film")));
    }


    @Test
    void deleteVideoGame() throws Exception{
        ObjectId objectid = new ObjectId("5f91658ec735df31bb0cf2dc");
        Mockito.when(videoGamesService.deleteVideoGame(ArgumentMatchers.any())).thenReturn("Successfully deleted video game");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/video-games/{gameId}", objectid.toHexString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully deleted video game"));
    }

    @Test
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