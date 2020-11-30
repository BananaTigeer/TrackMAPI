package com.rogelio.basecamp.TrackMAPI.tvseries;

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
class TVSeriesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TVSeriesService tvSeriesService;
    
    @Test
    @DisplayName("CREATE tv-series success")
    void createTVSeries() throws Exception{
        // Sets new TV Series to add then mock it to see if it works?
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockTVSeries = new TVSeries();
        mockTVSeries.setTvSerId(objectId);

        Mockito.when(tvSeriesService.createTVSeries(ArgumentMatchers.any())).thenReturn(mockTVSeries);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/tv-series")
                .content(asJsonString(mockTVSeries))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.tvSerId", is("5f91658ec735df31bb0cf2dc")));
    }

    @Test
    @DisplayName("GET all tv-series success")
    void getAllTVSeries() throws Exception{
        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2da");
        ObjectId objectId2 = new ObjectId("5f91658ec735df31bb0cf2db");
        ObjectId objectId3 = new ObjectId("5f91658ec735df31bb0cf2dc");

        TVSeries series1 = new TVSeries();
        series1.setTvSerId(objectId1);
        series1.setSeriesName("The Mandalorian Season 1");
        series1.setSeriesDescription("Space Western");
        series1.setDirector("Jon Favreau");

        TVSeries series2 = new TVSeries();
        series2.setTvSerId(objectId2);
        series2.setSeriesName("The Mandalorian Season 2");
        series2.setSeriesDescription("Space Western");
        series2.setDirector("Jon Favreau");

        TVSeries series3 = new TVSeries();
        series3.setTvSerId(objectId3);
        series3.setSeriesName("The Mandalorian Season 3");
        series3.setSeriesDescription("Space Western");
        series3.setDirector("Jon Favreau");

        List<TVSeries> series = new ArrayList<>();
        series.add(series1);
        series.add(series2);
        series.add(series3);

        doReturn(series).when(tvSeriesService).getAllTVSeries();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/tv-series"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].tvSerId", is("5f91658ec735df31bb0cf2da")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seriesName", is("The Mandalorian Season 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].seriesDescription", is("Space Western")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].director", is("Jon Favreau")));
    }

    @Test
    @DisplayName("GET tv-series success")
    void getTVSeries() throws Exception{
        ObjectId objectId1 = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockSeries = new TVSeries();
        mockSeries.setTvSerId(objectId1);
        mockSeries.setTvSerId(objectId1);
        mockSeries.setSeriesName("The Mandalorian");

        doReturn(mockSeries).when(tvSeriesService).getTVSeries(ArgumentMatchers.any(), ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/tv-series/{tvSerId}",objectId1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seriesName", is("The Mandalorian")));
    }

    @Test
    @DisplayName("PUT tv-series success")
    void putTVSeries() throws Exception {
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockSeries = new TVSeries();
        mockSeries.setTvSerId(objectId);
        mockSeries.setSeriesName("Test");
        mockSeries.setSeriesDescription("");
        mockSeries.setDirector("");

        //doReturn(mockMovie).when(moviesService).updateMovie(objectId, mockMovie);
        Mockito.when(tvSeriesService.putTVSeries(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockSeries);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/tv-series/{tvSerId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockSeries))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.seriesName", is("Test")))
                .andExpect(jsonPath("$.seriesDescription", is("")));
    }

    @Test
    @DisplayName("PATCH tv-series")
    void patchTVSeries() throws Exception{
        ObjectId objectId = new ObjectId("5f91658ec735df31bb0cf2dc");
        TVSeries mockSeries = new TVSeries();
        mockSeries.setTvSerId(objectId);
        mockSeries.setSeriesName("Test");
        mockSeries.setSeriesDescription("Space Western");

        Mockito.when(tvSeriesService.patchTVSeries(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockSeries);

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/tv-series/{tvSerId}", objectId.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockSeries))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.seriesName", is("Test")))
                .andExpect(jsonPath("$.seriesDescription", is("Space Western")));
    }

    @Test
    @DisplayName("DELETE tv-series success")
    void deleteTVSeries() throws Exception {
        ObjectId objectid = new ObjectId("5f91658ec735df31bb0cf2dc");
        Mockito.when(tvSeriesService.deleteTVSeries(ArgumentMatchers.any())).thenReturn("Successfully deleted TV series");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/tv-series/{tvSerId}", objectid.toHexString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully deleted TV series"));
    }

    @Test
    @DisplayName("DELETE all tv-series success")
    void deleteAllTVSeries() throws Exception{
        Mockito.when(tvSeriesService.deleteAllTVSeries()).thenReturn("Successfully deleted all TV Series");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/tv-series"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully deleted all TV Series"));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}