package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.TVSeries;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface TVSeriesService {
    void createTVSeries(TVSeries tvSeries);
    List<TVSeries> getAllTVSeries();
    TVSeries getTVSeries(ObjectId tvSerId);
    TVSeries updateTVSeries(ObjectId tvSerId, TVSeries tvSeries);
    void deleteTVSeries(ObjectId tvSerId);
    void deleteAllTVSeries();
}
