package com.rogelio.basecamp.TrackMAPI.Controllers;

import com.rogelio.basecamp.TrackMAPI.Models.VideoGame;
import com.rogelio.basecamp.TrackMAPI.Service.VideoGamesService;
import com.rogelio.basecamp.TrackMAPI.Service.VideoGamesServiceImplementation;
import com.rogelio.basecamp.TrackMAPI.errorhandlin.BadSyntaxException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/video-games")
public class VideoGamesController {

    @Autowired
    private VideoGamesService videoGamesService;

    @PostMapping("")
    public void createGame(@RequestBody VideoGame game){
        videoGamesService.createGame(game);
    }

    @GetMapping("")
    public List<VideoGame> getAllGames(){
        return videoGamesService.getAllGames();
    }

    @GetMapping("/{gameId}")
    public VideoGame getGame(@PathVariable String gameId){
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(gameId)){
            throw new BadSyntaxException("Invalid Id: " + gameId);
        }

        ObjectId objectId = new ObjectId(gameId);
        return videoGamesService.getGame(objectId);
    }

    @PutMapping("/{gameId}")
    public VideoGame putGame(@PathVariable String gameId, @RequestBody VideoGame game){
        if(!ObjectId.isValid(gameId)){
            throw new BadSyntaxException("Invalid Id: " + gameId);
        }

        ObjectId objectId = new ObjectId(gameId);

        return videoGamesService.putGame(objectId, game);
    }

    @PatchMapping("/{gameId}")
    public VideoGame patchGame(@PathVariable ObjectId gameId, @RequestBody VideoGame game){
        return videoGamesService.patchGame(gameId, game);
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable ObjectId gameId){
        videoGamesService.deleteGame(gameId);
    }

    @DeleteMapping("")
    public void deleteAllGames(){
        videoGamesService.deleteAllGame();
    }

}

