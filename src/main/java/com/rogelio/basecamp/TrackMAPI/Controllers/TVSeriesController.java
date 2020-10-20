package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.TVSeries;
import com.rogelio.basecamp.TrackMAPI.Service.TVSeriesServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tv-series")
public class TVSeriesController {

    @Autowired
    private TVSeriesServiceImplementation tvSeriesServiceImplementation;

    @GetMapping("")
    public List<TVSeries> getAllTVSeries(){
        return tvSeriesServiceImplementation.getAllTVSeries();
    }

    @GetMapping("/{tvSerId}")
    public TVSeries getTvSeries(@PathVariable int tvSerId)
    {
        return tvSeriesServiceImplementation.getTVSeries(tvSerId);
    }

}
