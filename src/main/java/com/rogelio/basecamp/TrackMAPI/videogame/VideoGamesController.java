package com.rogelio.basecamp.TrackMAPI.videogame;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.movie.Movie;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/video-games")
public class VideoGamesController {

    @Autowired
    private VideoGamesService videoGamesService;

    @PostMapping("")
    public ResponseEntity<VideoGame> createVideoGame(@RequestBody VideoGame VideoGame ){
        VideoGame createdGame = videoGamesService.createVideoGame(VideoGame);

        try{
            return ResponseEntity.created(new URI("/video-games"))
                    .body(createdGame);
        }catch(URISyntaxException e){
            throw new RuntimeException("Error in POST /video-games");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<VideoGame>> getAllVideoGames(){
        return ResponseEntity.ok().body(videoGamesService.getAllVideoGames());
    }

    @GetMapping("/{gameId}")
    public ResponseEntity getVideoGame(@Valid @RequestBody @PathVariable String gameId){
        return ResponseEntity.ok().body(videoGamesService.getVideoGame(gameId));
    }

    @PutMapping("/{gameId}")
    public ResponseEntity putVideoGame(@Valid @PathVariable String gameId, @RequestBody VideoGame VideoGame){
        return ResponseEntity.ok().body(videoGamesService.updateVideoGame(gameId, VideoGame));
    }

    @PatchMapping("/{gameId}")
    public ResponseEntity patchVideoGame(@Valid @PathVariable String gameId, @RequestBody VideoGame VideoGame){
        return ResponseEntity.ok().body(videoGamesService.updateVideoGame(gameId, VideoGame));
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity deleteVideoGame(@Valid @PathVariable String gameId){
        return ResponseEntity.ok().body(videoGamesService.deleteVideoGame(gameId));
    }

    @DeleteMapping("")
    public ResponseEntity deleteAllVideoGames(){
        return ResponseEntity.ok().body(videoGamesService.deleteAllVideoGames());
    }

}

