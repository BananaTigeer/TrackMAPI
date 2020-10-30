package com.rogelio.basecamp.TrackMAPI.videogame;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoGamesServiceImplementation implements VideoGamesService{

    @Autowired
    private GamesRepository gamesRepository;

    public VideoGamesServiceImplementation(){ }

    @Override
    public VideoGame createVideoGame(VideoGame videoGame) {
        return gamesRepository.save(videoGame);
    }

    @Override
    public List<VideoGame> getAllVideoGames() {
        return gamesRepository.findAll();
    }

    @Override
    public VideoGame getVideoGame(String gameId) {
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(gameId)){
            throw new BadRequestException("Invalid Id: " + gameId);
        }

        ObjectId objectId = new ObjectId(gameId);

        return gamesRepository.findById(objectId).orElseThrow(() -> new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist"));
    }

    @Override
    public VideoGame updateVideoGame(String gameId, VideoGame videoGame) {
        if(!ObjectId.isValid(gameId)){
            throw new BadRequestException("Invalid Id: " + gameId);
        }

        ObjectId objectId = new ObjectId(gameId);

        if(!gamesRepository.existsById(objectId)){
            throw new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist");
        }

        videoGame.setGameId(objectId);
        return gamesRepository.save(videoGame);
    }

    @Override
    public String deleteVideoGame(String gameId) {
        if(!ObjectId.isValid(gameId)){
            throw new BadRequestException("Invalid Id: " + gameId);
        }

        ObjectId objectId = new ObjectId(gameId);

        gamesRepository.deleteById(objectId);
        return "Successfully deleted video game";
    }


    @Override
    public String deleteAllVideoGames() {
        gamesRepository.deleteAll();
        return "Successfully deleted all video games";
    }
}
