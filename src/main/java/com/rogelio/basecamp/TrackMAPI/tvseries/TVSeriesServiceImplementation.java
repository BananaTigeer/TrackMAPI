package com.rogelio.basecamp.TrackMAPI.tvseries;

import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public TVSeries getTVSeries(ObjectId tvSerId) {
        return tvSeriesRepository.findById(tvSerId)
                .orElseThrow(() -> new RecordNotFoundException("Can't find " + tvSerId.toHexString() + ". It does not exist"));
    }

    @Override
    public TVSeries updateTVSeries(ObjectId tvSerId, TVSeries tvSeries) {
        if(!tvSeriesRepository.existsById(tvSerId)){
            throw new RecordNotFoundException("Can't find " + tvSerId.toHexString() + ". It does not exist");
        }

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
