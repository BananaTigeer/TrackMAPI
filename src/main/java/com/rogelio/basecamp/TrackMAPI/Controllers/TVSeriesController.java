package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.Games;
import com.rogelio.basecamp.TrackMAPI.Models.TVSeries;
import com.rogelio.basecamp.TrackMAPI.Service.TVSeriesServiceImplementation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tv-series")
public class TVSeriesController {

    @Autowired
    private TVSeriesServiceImplementation tvSeriesServiceImplementation;

    @PostMapping("")
    public void createTVSeries(@RequestBody TVSeries tvSeries){
        tvSeriesServiceImplementation.createTVSeries(tvSeries);
    }

    @GetMapping("")
    public List<TVSeries> getAllTVSeries(){
        return tvSeriesServiceImplementation.getAllTVSeries();
    }

    @GetMapping("/{tvSerId}")
    public Optional<TVSeries> getTVSeries(@PathVariable ObjectId tvSerId){
        return tvSeriesServiceImplementation.getTVSeries(tvSerId);
    }

    @PutMapping("/{tvSerId}")
    public TVSeries putTVSeries(@PathVariable ObjectId tvSerId, @RequestBody TVSeries tvSeries){
        return tvSeriesServiceImplementation.putTVSeries(tvSerId, tvSeries);
    }

    @PatchMapping("/{tvSerId}")
    public TVSeries patchTVSeries(@PathVariable ObjectId tvSerId, @RequestBody TVSeries tvSeries){
        return tvSeriesServiceImplementation.patchTVSeries(tvSerId, tvSeries);
    }

    @DeleteMapping("/{tvSerId}")
    public void deleteTVSeries(@PathVariable ObjectId tvSerId){
        tvSeriesServiceImplementation.deleteTVSeries(tvSerId);
    }

    @DeleteMapping("")
    public void deleteAllTVSeries(){

        tvSeriesServiceImplementation.deleteAllTVSeries();
    }

}
