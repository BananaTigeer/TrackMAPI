package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.Games;
import com.rogelio.basecamp.TrackMAPI.Repository.GameRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoGamesServiceImplementation implements VideoGamesService{

    @Autowired
    private GameRepository gameRepository;

    public VideoGamesServiceImplementation(){ }

    @Override
    public void createGame(Games game) {
        gameRepository.save(game);
    }

    @Override
    public List<Games> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Games> getGame(ObjectId gameId) {
        return gameRepository.findById(gameId);
    }

    @Override
    public Games putGame(ObjectId gameId, Games game) {
        game.setGameId(gameId);
        return gameRepository.save(game);
    }

    @Override
    public Games patchGame(ObjectId gameId, Games game) {
        game.setGameId(gameId);
        return gameRepository.save(game);
    }

    @Override
    public void deleteGame(ObjectId gameId) {
        gameRepository.deleteById(gameId);
    }

    @Override
    public void deleteAllGame() {
        gameRepository.deleteAll();
    }
}
