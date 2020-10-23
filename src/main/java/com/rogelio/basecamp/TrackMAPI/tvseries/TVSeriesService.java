package com.rogelio.basecamp.TrackMAPI.tvseries;

import org.bson.types.ObjectId;

import java.util.List;

public interface TVSeriesService {
    TVSeries createTVSeries(TVSeries tvSeries);
    List<TVSeries> getAllTVSeries();
    TVSeries getTVSeries(String tvSerId);
    TVSeries updateTVSeries(String tvSerId, TVSeries tvSeries);
    String deleteTVSeries(String tvSerId);
    String deleteAllTVSeries();
}
