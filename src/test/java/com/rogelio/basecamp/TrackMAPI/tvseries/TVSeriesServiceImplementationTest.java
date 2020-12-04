package com.rogelio.basecamp.TrackMAPI.tvseries;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TVSeriesServiceImplementationTest {

    @Autowired
    private TVSeriesService tvSeriesService;

    @MockBean
    private TVSeriesRepository tvSeriesRepository;

    @Test
    @DisplayName("save tv-series success ")
    void createTVSeries() {
        //Mocked movie and repository
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setSeriesName("The Mandalorian");
        mockTVSeries.setSeriesDescription("Western Scifi");
        mockTVSeries.setDirector("Jon Favraeu");

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);

        //Service
        TVSeries returnedTVSeries = tvSeriesService.createTVSeries(mockTVSeries);

        //Testing
        Assertions.assertNotNull(tvSeriesService, "Not null");
        Assertions.assertSame(returnedTVSeries, mockTVSeries, "same");
        Assertions.assertEquals(mockTVSeries.getSeriesName(), "The Mandalorian");
    }

    @Test
    @DisplayName("findAll tv-series success")
    void getAllTVSeries() {
        //Mocking retrieving collection of movies from repository
        TVSeries mockTVSeries1 = new TVSeries();
        mockTVSeries1.setSeriesName("The Mandalorian");
        mockTVSeries1.setSeriesDescription("Western Scifi");
        mockTVSeries1.setDirector("Jon Favraeu");

        TVSeries mockTVSeries2 = new TVSeries();
        mockTVSeries2.setSeriesName("The Expanse");
        mockTVSeries2.setSeriesDescription("Scifi");
        mockTVSeries2.setDirector("Mark Fergus");

        TVSeries mockTVSeries3 = new TVSeries();
        mockTVSeries2.setSeriesName("The Boys");
        mockTVSeries2.setSeriesDescription("Crime Drama Film");
        mockTVSeries2.setDirector("Eric Kripke");

        List<TVSeries> mockSeriesCollection = new ArrayList<TVSeries>();
        mockSeriesCollection.add(mockTVSeries1);
        mockSeriesCollection.add(mockTVSeries2);
        mockSeriesCollection.add(mockTVSeries3);

        Mockito.when(tvSeriesRepository.findAll()).thenReturn(mockSeriesCollection);

        //Service
        List<TVSeries> returnedSeriesCollection = tvSeriesService.getAllTVSeries();

        //Validate
        Assertions.assertFalse(returnedSeriesCollection.isEmpty());
        Assertions.assertSame(returnedSeriesCollection.get(0), mockTVSeries1);
        Assertions.assertEquals(returnedSeriesCollection.get(1).getSeriesName(), "The Boys");
    }

    @Test()
    @DisplayName("getTVSeries() series does not exist throws RecordNotFound exception")
    void getTVSeriesNotFound(){
        //Mocking game not found, throwing exception
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class, () -> {
            tvSeriesService.getTVSeries(mockRequest, "5f91658ec735df31bb0cf2dc");
        });

        assertEquals("Can't find 5f91658ec735df31bb0cf2dc. It does not exist", recordNotFoundException.getMessage());
    }

    @Test
    @DisplayName("findById tv-series invalid id exception success")
    void getTVSeriesInvalidId(){
        //Mocking invalid id throwing exception
        MockHttpServletRequest request = new MockHttpServletRequest();
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            tvSeriesService.getTVSeries(request, "123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update tv-series invalid id exception success")
    void putTVSeriesInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            tvSeriesService.putTVSeries("123", new TVSeries());
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update tv-series success")
    void putTVSeries() {
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setSeriesName("Test");

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);

        TVSeries returnedTVSeries = tvSeriesService.putTVSeries(objectId.toHexString(), mockTVSeries);

        Assertions.assertSame(returnedTVSeries, mockTVSeries);
    }

    @Test
    @DisplayName("patchTVSeries() valid id format")
    void patchTVSeriesValidId() {
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVseries = new TVSeries();
        mockTVseries.setTvSerId(objectId);
        mockTVseries.setSeriesName("Halo 3");
        mockTVseries.setSeriesDescription("First Person Shooter");

        Mockito.when(tvSeriesRepository.save(mockTVseries)).thenReturn(mockTVseries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVseries));

        //Service
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVseries.getTvSerId(), mockTVseries);

        //Testing
        Assertions.assertNotNull(returnedTVSeries, "Not null");
        Assertions.assertSame(returnedTVSeries, mockTVseries, "same");
    }

    @Test
    @DisplayName("patchTVSeries() invalid id format throw BadRequest exception")
    void patchTVSeriesInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            tvSeriesService.patchTVSeries("123", new TVSeries());
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    //region patchVideoGame() conditional branches
    @Test
    @DisplayName("patchTVSeries() getName is not null")
    void seriesNameIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setSeriesName("The Expanse");

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getSeriesName());
    }

    @Test
    @DisplayName("patchTVSeries() getName is null")
    void vgNameIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getSeriesName());
    }

    @Test
    @DisplayName("patchTVSeries() getDescription is not null")
    void seriesDescriptionIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setSeriesDescription("Sci-fi series spanning 4 seasons");

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getSeriesDescription());
    }

    @Test
    @DisplayName("patchTVSeries() getDescription is null")
    void seriesDescriptionIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getSeriesDescription());
    }

    @Test
    @DisplayName("patchTVSeries() getDirector is not null")
    void seriesDirectorIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setDirector("George Lucas");

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getDirector());
    }

    @Test
    @DisplayName("patchTVSeries() getDirector is null")
    void seriesDirectorIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getDirector());
    }

    @Test
    @DisplayName("patchTVSeries() getGenre is not null")
    void seriesGenreIsNotNull(){
        List<String> genre = new ArrayList<>();
        genre.add("Sci-fi");
        genre.add("Drama");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setGenre(genre);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getGenre());
    }

    @Test
    @DisplayName("patchTVSeries() getGenre is null")
    void seriesGenreIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getGenre());
    }

    @Test
    @DisplayName("patchTVSeries() getCreatedBy is not null")
    void seriesCreatedByIsNotNull(){
        List<String> createdBy = new ArrayList<>();
        createdBy.add("Night M. Shyamalayan");
        createdBy.add("David Prowse");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setCreatedBy(createdBy);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getCreatedBy());
    }

    @Test
    @DisplayName("patchTVSeries() getCreatedBy is null")
    void seriesCreatedByIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getCreatedBy());
    }

    @Test
    @DisplayName("patchTVSeries() getComposer is not null")
    void seriesComposerIsNotNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setComposer("John Williams");

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getComposer());
    }

    @Test
    @DisplayName("patchTVSeries() getComposer is null")
    void seriesComposerIsNull(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getComposer());
    }

    @Test
    @DisplayName("patchTVSeries() getNumOfSeasons is not 0")
    void seriesSeasonsIsNotEmpty(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setNumberOfSeasons(4);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertEquals(returnedTVSeries.getNumberOfSeasons(), 4);
    }

    @Test
    @DisplayName("patchTVSeries() getNumOfSeasons is 0")
    void seriesSeasonsIsEmpty(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertEquals(returnedTVSeries.getNumberOfSeasons(), 0);
    }

    @Test
    @DisplayName("patchTVSeries() getNumOfEpisodes is not 0")
    void seriesEpisodesIsNotEmpty(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setNumOfEpisodes(120);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertEquals(returnedTVSeries.getNumOfEpisodes(), 120);
    }

    @Test
    @DisplayName("patchTVSeries() getNumOfEpisodes is 0")
    void seriesEpisodesIsEmpty(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertEquals(returnedTVSeries.getNumOfEpisodes(), 0);
    }

    @Test
    @DisplayName("patchTVSeries() getCoverArtLink is not null")
    void seriesCoverArtLinkIsNotEmpty(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setCoverArtLink("Dummy Link");

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getCoverArtLink());
    }

    @Test
    @DisplayName("patchTVSeries() getCoverArtLink is null")
    void seriesCoverArtLinkIsEmpty(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getCoverArtLink());
    }

    @Test
    @DisplayName("patchTVSeries() getProductionCompany is not null")
    void seriesProductionCompanyIsNotEmpty(){
        List<String> prodCompany = new ArrayList<>();
        prodCompany.add("Bad Robot");
        prodCompany.add("LucasFilms");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setProductionCompany(prodCompany);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getProductionCompany());
    }

    @Test
    @DisplayName("patchTVSeries() getProductionCompany is null")
    void seriesProductionCompanyIsEmpty(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getProductionCompany());
    }

    @Test
    @DisplayName("patchTVSeries() getDistributer is not null")
    void seriesDestributedIsNotEmpty(){
        List<String> prodCompany = new ArrayList<>();
        prodCompany.add("Amazon");
        prodCompany.add("HBO");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setDistributer(prodCompany);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getDistributer());
    }

    @Test
    @DisplayName("patchTVSeries() getDistributer is null")
    void seriesDestributedIsEmpty(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getDistributer());
    }

    @Test
    @DisplayName("patchTVSeries() getRunningTime is not null")
    void seriesRunningTimeIsNotEmpty(){

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setRunningTime("30 minutes");

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getRunningTime());
    }

    @Test
    @DisplayName("patchTVSeries() getRunningTime is null")
    void seriesRunningTimeIsEmpty(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getRunningTime());
    }

    @Test
    @DisplayName("patchTVSeries() getActors is not null")
    void seriesActorsIsNotEmpty(){
        List<String> actors = new ArrayList<>();
        actors.add("Clark Kent");
        actors.add("Mara Jade");

        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setActors(actors);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNotNull(returnedTVSeries.getActors());
    }

    @Test
    @DisplayName("patchTVSeries() getActors is null")
    void seriesActorsIsEmpty(){
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        TVSeries returnedTVSeries = tvSeriesService.patchTVSeries(mockTVSeries.getTvSerId(), mockTVSeries);

        Assertions.assertNull(returnedTVSeries.getDistributer());
    }

    @Test
    @DisplayName("delete tv-series success")
    void deleteTVSeries() {
        tvSeriesService.deleteTVSeries("5f91658ec735df31bb0cf2dc");
        assertEquals(tvSeriesService.deleteTVSeries("5f91658ec735df31bb0cf2dc"), "Successfully deleted TV Series");
        }

    @Test
    @DisplayName("delete tv-series invalid id format throws Bad Request exception")
    void deleteTVSeriesInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            tvSeriesService.deleteTVSeries("123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("delete all tv-series success")
    void deleteAllTVSeries() {
        tvSeriesService.deleteAllTVSeries();
        assertEquals(tvSeriesService.deleteAllTVSeries(), "Successfully deleted all TV Series");
    }

}