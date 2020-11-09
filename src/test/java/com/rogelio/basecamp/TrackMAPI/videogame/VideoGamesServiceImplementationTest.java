package com.rogelio.basecamp.TrackMAPI.videogame;

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
class VideoGamesServiceImplementationTest {

    @Autowired
    private VideoGamesService videoGamesService;

    @MockBean
    GamesRepository gamesRepository;

    @Test
    @DisplayName("save video-game success")
    void createVideoGame() {
        //Mocked movie and repository
        VideoGame mockVideoGame = new VideoGame("Halo 3", "First Person Shooter", "Microsoft Game Studios");
        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);

        //Service
        VideoGame returnedVideoGame = videoGamesService.createVideoGame(mockVideoGame);

        //Testing
        Assertions.assertNotNull(videoGamesService, "Not null");
        Assertions.assertSame(returnedVideoGame, mockVideoGame, "same");
        Assertions.assertEquals(mockVideoGame.getGameName(), "Halo 3");
    }

    @Test
    @DisplayName("findAll all video-games success")
    void getAllVideoGames() {
        //Mocking retrieving collection of movies from repository
        VideoGame mockVideoGame1 = new VideoGame("Halo 3", "First Person Shooter", "Microsoft Game Studios");
        VideoGame mockVideoGame2 = new VideoGame("Halo 2", "First Person Shooter", "Microsoft Game Studios");
        VideoGame mockVideoGame3 = new VideoGame("Halo 4", "First Person Shooter", "Microsoft Game Studios");
        List<VideoGame> mockGamesCollection = new ArrayList<VideoGame>();
        mockGamesCollection.add(mockVideoGame1);
        mockGamesCollection.add(mockVideoGame2);
        mockGamesCollection.add(mockVideoGame3);
        Mockito.when(gamesRepository.findAll()).thenReturn(mockGamesCollection);

        //Service
        List<VideoGame> returnedGamesCollection = videoGamesService.getAllVideoGames();

        //Validate
        Assertions.assertFalse(returnedGamesCollection.isEmpty());
        Assertions.assertSame(returnedGamesCollection.get(0), mockVideoGame1);
        Assertions.assertEquals(returnedGamesCollection.get(1).getGameName(), "Halo 2");
    }

    @Test
    @DisplayName("findById video-game success")
    void getVideoGame() {
        //Mocking retrieving a movie from repository and assuming it's found
        VideoGame mockVideoGame = new VideoGame("Halo 3", "First Person Shooter", "Microsoft Game Studios");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockVideoGame.setGameId(objectId);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));

        //Service
        VideoGame returnedVideoGame = videoGamesService.getVideoGame(mockVideoGame.getGameId());

        Assertions.assertNotNull(returnedVideoGame);
        Assertions.assertSame(returnedVideoGame, mockVideoGame);
        Assertions.assertEquals(returnedVideoGame.getGameName(), "Halo 3");
    }

    @Test()
    @DisplayName("get video-game invalid id exception success")
    void getVideoGamesInvalidId(){
        //Mocking invalid id throwing exception
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            videoGamesService.getVideoGame("123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update video-game by invalid id exception success")
    void updateSeriesInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            videoGamesService.updateVideoGame("123", new VideoGame("Test", "Test", "Test"));
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update video-game success")
    void updateVideoGame() {
        VideoGame mockVideoGame = new VideoGame("Halo 3", "First Person Shooter", "Microsoft Game Studios");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameName("Test");
        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.existsById(objectId)).thenReturn(true);

        VideoGame returnedVideoGame = videoGamesService.updateVideoGame(objectId.toHexString(), mockVideoGame);

        Assertions.assertSame(returnedVideoGame, mockVideoGame);
    }

    @Test
    @DisplayName("delete video-game success")
    void deleteVideoGame() {
        videoGamesService.deleteVideoGame("5f91658ec735df31bb0cf2dc");
        assertEquals(videoGamesService.deleteVideoGame("5f91658ec735df31bb0cf2dc"), "Successfully deleted video game");
    }

    @Test
    @DisplayName("delete video-game by invalid id throws bad request success")
    void deleteVideoGameInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            videoGamesService.deleteVideoGame("123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("delete all video-games success")
    void deleteAllVideoGames() {
        videoGamesService.deleteAllVideoGames();
        assertEquals(videoGamesService.deleteAllVideoGames(), "Successfully deleted all video games");
    }
}