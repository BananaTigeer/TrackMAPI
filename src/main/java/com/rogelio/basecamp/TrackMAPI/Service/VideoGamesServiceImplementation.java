package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.VideoGame;
import com.rogelio.basecamp.TrackMAPI.Repository.GamesRepository;
import com.rogelio.basecamp.TrackMAPI.errorhandlin.RecordNotFoundException;
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
    public VideoGame getGame(ObjectId gameId) {
        return gamesRepository.findById(gameId).orElseThrow(() -> new RecordNotFoundException("Can't find " + gameId.toHexString() + ". It does not exist"));
    }

    @Override
    public VideoGame updateUser(ObjectId gameId, VideoGame game) {
        if(!gamesRepository.existsById(gameId)){
            throw new RecordNotFoundException("Can't find " + gameId.toHexString() + ". It does not exist");
        }

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
