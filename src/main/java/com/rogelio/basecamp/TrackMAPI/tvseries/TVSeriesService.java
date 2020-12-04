package com.rogelio.basecamp.TrackMAPI.tvseries;

import org.bson.types.ObjectId;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TVSeriesService {
    TVSeries createTVSeries(TVSeries tvSeries);
    List<TVSeries> getAllTVSeries();
    TVSeries getTVSeries(HttpServletRequest request, String tvSerId);
    TVSeries putTVSeries(String tvSerId, TVSeries tvSeries);
    TVSeries patchTVSeries(String tvSerId, TVSeries tvSeries);
    String deleteTVSeries(String tvSerId);
    String deleteAllTVSeries();
}
