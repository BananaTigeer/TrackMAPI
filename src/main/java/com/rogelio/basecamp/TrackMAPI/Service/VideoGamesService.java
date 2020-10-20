package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.VideoGame;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface VideoGamesService {
    abstract void createGame(VideoGame game);
    abstract List<VideoGame> getAllGames();
    abstract Optional<VideoGame> getGame(ObjectId gameId);
    abstract VideoGame putGame(ObjectId gameId, VideoGame game);
    abstract VideoGame patchGame(ObjectId gameId, VideoGame game);
    abstract void deleteGame(ObjectId gameId);
    abstract void deleteAllGame();
}
