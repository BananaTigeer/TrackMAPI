package com.rogelio.basecamp.TrackMAPI.tvseries;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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

    @Disabled
    @Test
    @DisplayName("findById tv-series success")
    void getTVSeries() {
        //Mocking retrieving a movie from repository and assuming it's found
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);
        mockTVSeries.setSeriesName("The Mandalorian");
        mockTVSeries.setSeriesDescription("Western Scifi");
        mockTVSeries.setDirector("Jon Favraeu");

        Mockito.when(tvSeriesRepository.findById(objectId)).thenReturn(Optional.of(mockTVSeries));
        Mockito.when(tvSeriesRepository.existsById(objectId)).thenReturn(true);

        //Service
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        TVSeries returnedSeries = tvSeriesService.getTVSeries(mockRequest, mockTVSeries.getTvSerId());

        Assertions.assertNotNull(returnedSeries);
        Assertions.assertSame(returnedSeries, mockTVSeries);
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