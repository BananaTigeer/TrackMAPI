package com.rogelio.basecamp.TrackMAPI.videogame;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
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
    private GamesRepository gamesRepository;

    @Test
    @DisplayName("save video-game success")
    void createVideoGame() {
        //Mocked movie and repository
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameName("Halo 3");
        mockVideoGame.setGameDescription("First Person Shooter");
        mockVideoGame.setPublisher("Microsoft Game Studios");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);

        //Service
        VideoGame returnedVideoGame = videoGamesService.createVideoGame(mockVideoGame);

        //Testing
        Assertions.assertNotNull(videoGamesService, "Not null");
        Assertions.assertSame(returnedVideoGame, mockVideoGame, "same");
    }

    @Test
    @DisplayName("findAll all video-games success")
    void getAllVideoGames() {
        //Mocking retrieving collection of movies from repository
        VideoGame mockVideoGame1 = new VideoGame();
        mockVideoGame1.setGameName("Halo 3");
        mockVideoGame1.setGameDescription("First Person Shooter");
        mockVideoGame1.setPublisher("Microsoft Game Studios");

        VideoGame mockVideoGame2 = new VideoGame();
        mockVideoGame2.setGameName("Halo 2");
        mockVideoGame2.setGameDescription("First Person Shooter");
        mockVideoGame2.setPublisher("Microsoft Game Studios");

        VideoGame mockVideoGame3 = new VideoGame();
        mockVideoGame3.setGameName("Halo 4");
        mockVideoGame3.setGameDescription("First Person Shooter");
        mockVideoGame3.setPublisher("Microsoft Game Studios");

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
        Assertions.assertSame(returnedGamesCollection.get(1), mockVideoGame2);
        Assertions.assertSame(returnedGamesCollection.get(2), mockVideoGame3);
    }

    @Disabled
    @Test
    @DisplayName("getVideoGame() valid id format")
    void getVideoGame() {
        //Mocking retrieving a movie from repository and assuming it's found
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameName("Halo 3");
        mockVideoGame.setGameDescription("First Person Shooter");
        mockVideoGame.setPublisher("Microsoft Game Studios");

        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        Mockito.when(gamesRepository.existsById(objectId)).thenReturn(true);

        //Service
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        VideoGame returnedVideoGame = videoGamesService.getVideoGame(mockRequest, mockVideoGame.getGameId());

        Assertions.assertNotNull(returnedVideoGame);
        Assertions.assertSame(returnedVideoGame, mockVideoGame);
    }

    @Test()
    @DisplayName("getVideoGame() videogame does not exist throws RecordNotFound exception")
    void getVideoGamesNotFound(){
        //Mocking game not found, throwing exception
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class, () -> {
            videoGamesService.getVideoGame(mockRequest, "5f91658ec735df31bb0cf2dc");
        });

        assertEquals("Can't find 5f91658ec735df31bb0cf2dc. It does not exist", recordNotFoundException.getMessage());
    }

    @Test()
    @DisplayName("getVideoGame() invalid id format throws BadRequest exception")
    void getVideoGamesInvalidId(){
        //Mocking invalid id throwing exception
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            videoGamesService.getVideoGame(mockRequest, "123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("putVideoGame() invalid id format throws BadRequest exception")
    void putSeriesInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            videoGamesService.putVideoGame("123", new VideoGame());
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("putVideoGame() valid id format")
    void putVideoGame() {
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameName("Halo 3");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.existsById(objectId)).thenReturn(true);

        VideoGame returnedVideoGame = videoGamesService.putVideoGame(objectId.toHexString(), mockVideoGame);

        Assertions.assertNotNull(returnedVideoGame);
        Assertions.assertSame(returnedVideoGame, mockVideoGame);
    }

    @Test
    @DisplayName("patchVideoGame() valid id format")
    void patchVgValidId() {
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameName("Halo 3");
        mockVideoGame.setGameDescription("First Person Shooter");
        mockVideoGame.setPublisher("Microsoft Game Studios");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));

        //Service
        VideoGame returnedVideoGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        //Testing
        Assertions.assertNotNull(videoGamesService, "Not null");
        Assertions.assertSame(returnedVideoGame, mockVideoGame, "same");
    }

    @Test
    @DisplayName("patchVideoGame() invalid id format throw BadRequest exception")
    void patchVgInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            videoGamesService.patchVideoGame("123", new VideoGame());
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    //region patchVideoGame() conditional branches
    @Test
    @DisplayName("patchVideoGame() getName is not null")
    void vgNameIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameName("Halo 3");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getGameName());
    }

    @Test
    @DisplayName("patchVideoGame() getName is null")
    void vgNameIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getGameName());
    }

    @Test
    @DisplayName("patchVideoGame() getDescription is not null")
    void vgDescriptionIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGameDescription("First Person SHooter");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getGameDescription());
    }

    @Test
    @DisplayName("patchVideoGame() getDescription is null")
    void vgDescriptionIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getGameDescription());
    }

    @Test
    @DisplayName("patchVideoGame() getDateReleased is not null")
    void vgDateReleasedIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setDateReleased("Nov. 1, 2020");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getDateReleased());
    }

    @Test
    @DisplayName("patchVideoGame() getDateReleased is null")
    void vgDateReleasedIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getDateReleased());
    }

    @Test
    @DisplayName("patchVideoGame() getPublisher is not null")
    void vgPublisherIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setPublisher("Microsoft Game Studios");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getPublisher());
    }

    @Test
    @DisplayName("patchVideoGame() getPublisher is null")
    void vgPublisherIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getPublisher());
    }

    @Test
    @DisplayName("patchVideoGame() getDeveloper is not null")
    void vgDeveloperIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setDeveloper("Bungie");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getDeveloper());
    }

    @Test
    @DisplayName("patchVideoGame() getDeveloper is null")
    void vgDeveloperIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getDeveloper());
    }

    @Test
    @DisplayName("patchVideoGame() getComposer is not null")
    void vgComposerIsNotNull(){
        List<String> composers = new ArrayList<>();
        composers.add("Marty O' Donnel");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setComposer(composers);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getComposer());
    }

    @Test
    @DisplayName("patchVideoGame() getComposer is null")
    void vgComposerIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getComposer());
    }

    @Test
    @DisplayName("patchVideoGame() getCoverArtLink is not null")
    void vgCoverArtLinkIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setCoverArtLink("www.dummylink.com");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getCoverArtLink());
    }

    @Test
    @DisplayName("patchVideoGame() getCoverArtLink is null")
    void vgCoverArtLinkIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getCoverArtLink());
    }

    @Test
    @DisplayName("patchVideoGame() getGenre is not null")
    void vgGenreIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setGenre("First Person Shooter");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getGenre());
    }

    @Test
    @DisplayName("patchVideoGame() getGenre is null")
    void vgGenreIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getGenre());
    }

    @Test
    @DisplayName("patchVideoGame() getModes is not null")
    void vgModesIsNotNull(){
        List<String> modes = new ArrayList<>();
        modes.add("Single-Player");
        modes.add("Onlie Multi-Player");
        modes.add("Co-op");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setModes(modes);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getModes());
    }

    @Test
    @DisplayName("patchVideoGame() getModes is null")
    void vgModesIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getModes());
    }

    @Test
    @DisplayName("patchVideoGame() getEngine is not null")
    void vgEngineIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setEngine("Propriety Game Engine");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getEngine());
    }

    @Test
    @DisplayName("patchVideoGame() getEngine is null")
    void vgEngineIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getEngine());
    }

    @Test
    @DisplayName("patchVideoGame() getWriter is not null")
    void vgWriterIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);
        mockVideoGame.setWriter("Joseph Staten");

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNotNull(returnedGame.getWriter());
    }

    @Test
    @DisplayName("patchVideoGame() getWriter is null")
    void vgWriterIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        VideoGame mockVideoGame = new VideoGame();
        mockVideoGame.setGameId(objectId);

        Mockito.when(gamesRepository.save(mockVideoGame)).thenReturn(mockVideoGame);
        Mockito.when(gamesRepository.findById(objectId)).thenReturn(Optional.of(mockVideoGame));
        VideoGame returnedGame = videoGamesService.patchVideoGame(mockVideoGame.getGameId(), mockVideoGame);

        Assertions.assertNull(returnedGame.getWriter());
    }
    //endregion

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