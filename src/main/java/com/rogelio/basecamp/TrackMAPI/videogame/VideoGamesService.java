package com.rogelio.basecamp.TrackMAPI.videogame;

import org.bson.types.ObjectId;

import java.util.List;

public interface VideoGamesService {
    void createGame(VideoGame game);
    List<VideoGame> getAllGames();
    VideoGame getGame(ObjectId gameId);
    VideoGame updateUser(ObjectId gameId, VideoGame game);
    void deleteGame(ObjectId gameId);
    void deleteAllGame();
}
