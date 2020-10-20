package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.Games;
import com.rogelio.basecamp.TrackMAPI.Service.VideoGamesServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video-games")
public class VideoGamesController {

    @Autowired
    private VideoGamesServiceImplementation videoGamesServiceImplementation;

    @GetMapping("")
    public List<Games> getAllVideoGames(){
        return videoGamesServiceImplementation.getAllGames();
    }

    @GetMapping("/{gameId}")
    public Games getVideoGame(@PathVariable int gameId){
        return videoGamesServiceImplementation.getGame(gameId);
    }

    @PostMapping("")
    public void createGame(@RequestBody Games game){
        videoGamesServiceImplementation.createVideoGame(game);
    }

}
