package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.TVSeries;
import com.rogelio.basecamp.TrackMAPI.Service.TVSeriesService;
import com.rogelio.basecamp.TrackMAPI.errorhandlin.BadSyntaxException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/tv-series")
public class TVSeriesController {

    @Autowired
    private TVSeriesService tvSeriesService;

    @PostMapping("")
    public void createTVSeries(@RequestBody TVSeries tvSeries){
        tvSeriesService.createTVSeries(tvSeries);
    }

    @GetMapping("")
    public List<TVSeries> getAllTVSeries(){
        return tvSeriesService.getAllTVSeries();
    }

    @GetMapping("/{tvSerId}")
    public TVSeries getTVSeries(@PathVariable String tvSerId){
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(tvSerId)){
            throw new BadSyntaxException("Invalid Id: " + tvSerId);
        }

        ObjectId objectId = new ObjectId(tvSerId);
        return tvSeriesService.getTVSeries(objectId);
    }

    @PutMapping("/{tvSerId}")
    public TVSeries putTVSeries(@PathVariable String tvSerId, @RequestBody TVSeries tvSeries){
        if(!ObjectId.isValid(tvSerId)){
            throw new BadSyntaxException("Invalid Id: " + tvSerId);
        }

        ObjectId objectId = new ObjectId(tvSerId);
        return tvSeriesService.updateTVSeries(objectId, tvSeries);
    }

    @PatchMapping("/{tvSerId}")
    public TVSeries patchTVSeries(@PathVariable ObjectId tvSerId, @RequestBody TVSeries tvSeries){
        return tvSeriesService.updateTVSeries(tvSerId, tvSeries);
    }

    @DeleteMapping("/{tvSerId}")
    public void deleteTVSeries(@PathVariable ObjectId tvSerId){
        tvSeriesService.deleteTVSeries(tvSerId);
    }

    @DeleteMapping("")
    public void deleteAllTVSeries(){

        tvSeriesService.deleteAllTVSeries();
    }

}
