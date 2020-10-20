package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.Games;
import com.rogelio.basecamp.TrackMAPI.Service.VideoGamesServiceImplementation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/video-games")
public class VideoGamesController {

    @Autowired
    private VideoGamesServiceImplementation videoGamesServiceImplementation;

    @PostMapping("")
    public void createGame(@RequestBody Games game){
        videoGamesServiceImplementation.createGame(game);
    }

    @GetMapping("")
    public List<Games> getAllGames(){
        return videoGamesServiceImplementation.getAllGames();
    }

    @GetMapping("/{gameId}")
    public Optional<Games> getGame(@PathVariable ObjectId gameId){
        return videoGamesServiceImplementation.getGame(gameId);
    }

    @PutMapping("/{gameId}")
    public Games putGame(@PathVariable ObjectId gameId, @RequestBody Games game){
        return videoGamesServiceImplementation.putGame(gameId, game);
    }

    @PatchMapping("/{gameId}")
    public Games patchGame(@PathVariable ObjectId gameId, @RequestBody Games game){
        return videoGamesServiceImplementation.patchGame(gameId, game);
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable ObjectId gameId){
        videoGamesServiceImplementation.deleteGame(gameId);
    }

    @DeleteMapping("")
    public void deleteAllGames(){
        videoGamesServiceImplementation.deleteAllGame();
    }

}

