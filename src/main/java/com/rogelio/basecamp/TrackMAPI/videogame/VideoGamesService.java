package com.rogelio.basecamp.TrackMAPI.videogame;

import org.bson.types.ObjectId;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VideoGamesService {
    VideoGame createVideoGame(VideoGame videoGame);
    List<VideoGame> getAllVideoGames();
    VideoGame getVideoGame(HttpServletRequest request, String gameId);
    VideoGame putVideoGame(String gameId, VideoGame videoGame);
    VideoGame patchVideoGame(String gameId, VideoGame videoGame);
    String deleteVideoGame(String gameId);
    String deleteAllVideoGames();
}
