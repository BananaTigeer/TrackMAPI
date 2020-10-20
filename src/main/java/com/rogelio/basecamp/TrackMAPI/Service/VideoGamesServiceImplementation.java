package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Games;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoGamesServiceImplementation {

    private List<Games> games;

    public VideoGamesServiceImplementation(){
        this.games = new ArrayList<>();
        games.add(new Games(1, "Doom 2019", "Kill Demons", "Bethesda"));
        games.add(new Games(2, "Halo Infinite", "Finishing the fight... again", "Microsoft"));
    }

    public List<Games> getAllGames(){
        return this.games;
    }

    public Games getGame(int gameId){
        for(Games game : this.games){
            if(game.getGameId() == gameId){
                return game;
            }
        }
        return null;
    }

    public void createVideoGame(Games game){
        this.games.add(game);
    }

}
