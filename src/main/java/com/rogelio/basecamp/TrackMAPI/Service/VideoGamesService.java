package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Games;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface VideoGamesService {
    abstract void createGame(Games game);
    abstract List<Games> getAllGames();
    abstract Optional<Games> getGame(ObjectId gameId);
    abstract Games putGame(ObjectId gameId, Games game);
    abstract Games patchGame(ObjectId gameId, Games game);
    abstract void deleteGame(ObjectId gameId);
    abstract void deleteAllGame();
}
