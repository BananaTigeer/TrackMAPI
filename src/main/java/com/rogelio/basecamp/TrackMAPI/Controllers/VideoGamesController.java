package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.VideoGame;
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
    public void createGame(@RequestBody VideoGame game){
        videoGamesServiceImplementation.createGame(game);
    }

    @GetMapping("")
    public List<VideoGame> getAllGames(){
        return videoGamesServiceImplementation.getAllGames();
    }

    @GetMapping("/{gameId}")
    public Optional<VideoGame> getGame(@PathVariable ObjectId gameId){
        return videoGamesServiceImplementation.getGame(gameId);
    }

    @PutMapping("/{gameId}")
    public VideoGame putGame(@PathVariable ObjectId gameId, @RequestBody VideoGame game){
        return videoGamesServiceImplementation.putGame(gameId, game);
    }

    @PatchMapping("/{gameId}")
    public VideoGame patchGame(@PathVariable ObjectId gameId, @RequestBody VideoGame game){
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

