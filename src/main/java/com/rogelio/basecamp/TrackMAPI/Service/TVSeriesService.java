package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.TVSeries;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface TVSeriesService {
    abstract void createTVSeries(TVSeries tvSeries);
    abstract List<TVSeries> getAllTVSeries();
    abstract Optional<TVSeries> getTVSeries(ObjectId tvSerId);
    abstract TVSeries putTVSeries(ObjectId tvSerId, TVSeries tvSeries);
    abstract TVSeries patchTVSeries(ObjectId tvSerId, TVSeries tvSeries);
    abstract void deleteTVSeries(ObjectId tvSerId);
    abstract void deleteAllTVSeries();
}
