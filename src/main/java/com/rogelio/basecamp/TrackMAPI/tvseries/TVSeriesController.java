package com.rogelio.basecamp.TrackMAPI.tvseries;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Validated
@RequestMapping("/tv-series")
public class TVSeriesController {

    @Autowired
    private TVSeriesService tvSeriesService;

    @PostMapping("")
    public ResponseEntity<TVSeries> createTVSeries(@RequestBody TVSeries tvSeries){
        TVSeries createdTVSeries = tvSeriesService.createTVSeries(tvSeries);

        try{
            return ResponseEntity.created(new URI("/tv-series"))
                    .body(createdTVSeries);
        }catch(URISyntaxException e){
            throw new RuntimeException("Error in POST /tv-series");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<TVSeries>> getAllTVSeries(){
        return ResponseEntity.ok().body(tvSeriesService.getAllTVSeries());
    }

    @GetMapping("/{tvSerId}")
    public ResponseEntity getTVSeries(HttpServletRequest request, @PathVariable String tvSerId){
        return ResponseEntity.ok().body(tvSeriesService.getTVSeries(request, tvSerId));
    }

    @PutMapping("/{tvSerId}")
    public ResponseEntity putTVSeries(@PathVariable String tvSerId, @RequestBody TVSeries tvSeries){
        return ResponseEntity.ok().body(tvSeriesService.putTVSeries(tvSerId, tvSeries));
    }

    @PatchMapping("/{tvSerId}")
    public ResponseEntity patchTVSeries(@PathVariable String tvSerId, @RequestBody TVSeries tvSeries){
        return ResponseEntity.ok().body(tvSeriesService.patchTVSeries(tvSerId, tvSeries));
    }

    @DeleteMapping("/{tvSerId}")
    public ResponseEntity deleteTVSeries(@PathVariable String tvSerId){
        return ResponseEntity.ok().body(tvSeriesService.deleteTVSeries(tvSerId));
    }

    @DeleteMapping("")
    public ResponseEntity deleteAllTVSeries(){
        return ResponseEntity.ok().body(tvSeriesService.deleteAllTVSeries());
    }

}
