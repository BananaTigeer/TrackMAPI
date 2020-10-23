package com.rogelio.basecamp.TrackMAPI.videogame;

import org.bson.types.ObjectId;

import java.util.List;

public interface VideoGamesService {
    VideoGame createVideoGame(VideoGame videoGame);
    List<VideoGame> getAllVideoGames();
    VideoGame getVideoGame(String gameId);
    VideoGame updateVideoGame(String gameId, VideoGame videoGame);
    String deleteVideoGame(String gameId);
    String deleteAllVideoGames();
}
