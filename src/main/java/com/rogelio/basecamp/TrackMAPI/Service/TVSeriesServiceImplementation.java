package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.TVSeries;
import com.rogelio.basecamp.TrackMAPI.Repository.TVSeriesRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TVSeriesServiceImplementation implements TVSeriesService{

    @Autowired
    private TVSeriesRepository tvSeriesRepository;

    public TVSeriesServiceImplementation(){}

    @Override
    public void createTVSeries(TVSeries tvSeries) {
        tvSeriesRepository.save(tvSeries);
    }

    @Override
    public List<TVSeries> getAllTVSeries() {
        return tvSeriesRepository.findAll();
    }

    @Override
    public Optional<TVSeries> getTVSeries(ObjectId tvSerId) {
        return tvSeriesRepository.findById(tvSerId);
    }

    @Override
    public TVSeries putTVSeries(ObjectId tvSerId, TVSeries tvSeries) {
        tvSeries.setTvSerId(tvSerId);
        return tvSeriesRepository.save(tvSeries);
    }

    @Override
    public TVSeries patchTVSeries(ObjectId tvSerId, TVSeries tvSeries) {
        tvSeries.setTvSerId(tvSerId);
        return tvSeriesRepository.save(tvSeries);
    }

    @Override
    public void deleteTVSeries(ObjectId tvSerId) {
        tvSeriesRepository.deleteById(tvSerId);
    }

    @Override
    public void deleteAllTVSeries() {
        tvSeriesRepository.deleteAll();
    }
}
