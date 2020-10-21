package com.rogelio.basecamp.TrackMAPI.tvseries;

import org.bson.types.ObjectId;

import java.util.List;

public interface TVSeriesService {
    void createTVSeries(TVSeries tvSeries);
    List<TVSeries> getAllTVSeries();
    TVSeries getTVSeries(ObjectId tvSerId);
    TVSeries updateTVSeries(ObjectId tvSerId, TVSeries tvSeries);
    void deleteTVSeries(ObjectId tvSerId);
    void deleteAllTVSeries();
}
