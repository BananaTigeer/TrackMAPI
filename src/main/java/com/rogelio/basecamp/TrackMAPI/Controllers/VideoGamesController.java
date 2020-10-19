package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.Games;
import com.rogelio.basecamp.TrackMAPI.Service.VideoGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video-games")
public class VideoGamesController {

    @Autowired
    private VideoGamesService videoGamesService;

    @GetMapping("")
    public List<Games> getAllVideoGames(){
        return videoGamesService.getAllGames();
    }

    @GetMapping("/{gameId}")
    public Games getVideoGame(@PathVariable int gameId){
        return videoGamesService.getGame(gameId);
    }

    @PostMapping("")
    public void createGame(@RequestBody Games game){
        videoGamesService.createVideoGame(game);
    }

}
