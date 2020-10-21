package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.VideoGame;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface VideoGamesService {
    void createGame(VideoGame game);
    List<VideoGame> getAllGames();
    VideoGame getGame(ObjectId gameId);
    VideoGame updateUser(ObjectId gameId, VideoGame game);
    void deleteGame(ObjectId gameId);
    void deleteAllGame();
}
