package com.rogelio.basecamp.TrackMAPI.tvseries;

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
class TVSeriesServiceImplementationTest {

    @Autowired
    private TVSeriesService tvSeriesService;

    @MockBean
    TVSeriesRepository tvSeriesRepository;

    @Test
    @DisplayName("save tv-series success ")
    void createTVSeries() {
        //Mocked movie and repository
        TVSeries mockTVSeries = new TVSeries("The Mandalorian", "Western Scifi", "Jon Favraeu");
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
        TVSeries mockTVSeries1 = new TVSeries("The Mandalorian", "Western Scifi", "Jon Favraeu");
        TVSeries mockTVSeries2 = new TVSeries("The Expanse", "Scifi", "Mark Fergus");
        TVSeries mockTVSeries3 = new TVSeries("The Boys", "Crime Drama Film", "Eric Kripke");
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
        Assertions.assertEquals(returnedSeriesCollection.get(1).getSeriesName(), "The Expanse");
    }

    @Test
    @DisplayName("findById tv-series success")
    void getTVSeries() {
        //Mocking retrieving a movie from repository and assuming it's found
        TVSeries mockTVSeries = new TVSeries("The Mandalorian", "Western Scifi", "Jon Favraeu");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockTVSeries.setTvSerId(objectId);
        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));

        //Service
        TVSeries returnedSeries = tvSeriesService.getTVSeries(mockTVSeries.getTvSerId());

        Assertions.assertNotNull(returnedSeries);
        Assertions.assertSame(returnedSeries, mockTVSeries);
        Assertions.assertEquals(returnedSeries.getSeriesName(), "The Mandalorian");
    }

    @Test
    @DisplayName("findById tv-series invalid id exception success")
    void getTVSeriesInvalidId(){
        //Mocking invalid id throwing exception
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            tvSeriesService.getTVSeries("123");
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update tv-series invalid id exception success")
    void updateSeriesInvalidId() {
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
            tvSeriesService.updateTVSeries("123", new TVSeries("Test", "Test", "Test"));
        });

        assertEquals("Invalid Id: 123", badRequestException.getMessage());
    }

    @Test
    @DisplayName("update tv-series success")
    void updateTVSeries() {
        TVSeries mockTVSeries = new TVSeries("The Mandalorian", "Western Scifi", "Jon Favraeu");
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setSeriesName("Test");
        Mockito.when(tvSeriesRepository.save(mockTVSeries)).thenReturn(mockTVSeries);

        TVSeries returnedTVSeries = tvSeriesService.updateTVSeries(objectId.toHexString(), mockTVSeries);

        Assertions.assertSame(returnedTVSeries, mockTVSeries);
    }

    @Test
    @DisplayName("delete tv-series success")
    void deleteTVSeries() {
        tvSeriesService.deleteTVSeries("5f91658ec735df31bb0cf2dc");
        assertEquals(tvSeriesService.deleteTVSeries("5f91658ec735df31bb0cf2dc"), "Successfully deleted TV Series");
        }

    @Test
    @DisplayName("delete all tv-series success")
    void deleteAllTVSeries() {
        tvSeriesService.deleteAllTVSeries();
        assertEquals(tvSeriesService.deleteAllTVSeries(), "Successfully deleted all TV Series");
    }
}