package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.TVSeries;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TVSeriesService {

    private List<TVSeries> tvSeries;

    public TVSeriesService(){
        this.tvSeries = new ArrayList<>();
        tvSeries.add(new TVSeries(1, "The Mandalorian", "WesternSciFi", "Jon Favreau"));
        tvSeries.add(new TVSeries(2, "The Wire", "Crime Drama", "David Simon"));
    }

    public List<TVSeries> getAllTVSeries(){
        return this.tvSeries;
    }

    public TVSeries getTVSeries(int tvSerId){
        for(TVSeries tvSeries : this.tvSeries){
            if(tvSeries.getTvSerId() == tvSerId){
                return tvSeries;
            }
        }

        return null;
    }
}
