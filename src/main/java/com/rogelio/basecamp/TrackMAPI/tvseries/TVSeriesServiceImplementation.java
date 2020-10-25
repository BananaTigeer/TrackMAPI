package com.rogelio.basecamp.TrackMAPI.tvseries;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.NoContentException;
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
    public TVSeries createTVSeries(TVSeries tvSeries) {
        return tvSeriesRepository.save(tvSeries);
    }

    @Override
    public List<TVSeries> getAllTVSeries() {
        return tvSeriesRepository.findAll();
    }

    @Override
    public TVSeries getTVSeries(String tvSerId) {
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(tvSerId)){
            throw new BadRequestException("Invalid Id: " + tvSerId);
        }

        ObjectId objectId = new ObjectId(tvSerId);

        return tvSeriesRepository.findById(objectId)
                .orElseThrow(() -> new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist"));
    }

    @Override
    public TVSeries updateTVSeries(String tvSerId, TVSeries tvSeries) {
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(tvSerId)){
            throw new BadRequestException("Invalid Id: " + tvSerId);
        }

        ObjectId objectId = new ObjectId(tvSerId);

        tvSeries.setTvSerId(objectId);
        return tvSeriesRepository.save(tvSeries);
    }

    @Override
    public String deleteTVSeries(String tvSerId) {
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(tvSerId)){
            throw new BadRequestException("Invalid Id: " + tvSerId);
        }

        ObjectId objectId = new ObjectId(tvSerId);

        tvSeriesRepository.deleteById(objectId);
        return "Successfully deleted TV Series";
    }

    @Override
    public String deleteAllTVSeries() {
        tvSeriesRepository.deleteAll();
        return "Successfully deleted all TV Series";
    }
}
