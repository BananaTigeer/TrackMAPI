package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.VideoGame;
import com.rogelio.basecamp.TrackMAPI.Repository.GamesRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoGamesServiceImplementation implements VideoGamesService{

    @Autowired
    private GamesRepository gamesRepository;

    public VideoGamesServiceImplementation(){ }

    @Override
    public void createGame(VideoGame game) {
        gamesRepository.save(game);
    }

    @Override
    public List<VideoGame> getAllGames() {
        return gamesRepository.findAll();
    }

    @Override
    public Optional<VideoGame> getGame(ObjectId gameId) {
        return gamesRepository.findById(gameId);
    }

    @Override
    public VideoGame putGame(ObjectId gameId, VideoGame game) {
        game.setGameId(gameId);
        return gamesRepository.save(game);
    }

    @Override
    public VideoGame patchGame(ObjectId gameId, VideoGame game) {
        game.setGameId(gameId);
        return gamesRepository.save(game);
    }

    @Override
    public void deleteGame(ObjectId gameId) {
        gamesRepository.deleteById(gameId);
    }

    @Override
    public void deleteAllGame() {
        gamesRepository.deleteAll();
    }
}
