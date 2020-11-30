package com.rogelio.basecamp.TrackMAPI.videogame;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
import com.rogelio.basecamp.TrackMAPI.user.User;
import com.rogelio.basecamp.TrackMAPI.user.UsersService;
import org.bson.types.ObjectId;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class VideoGamesServiceImplementation implements VideoGamesService{

    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private UsersService usersService;


    @Override
    public VideoGame createVideoGame(VideoGame videoGame) {
        return gamesRepository.save(videoGame);
    }

    @Override
    public List<VideoGame> getAllVideoGames() {
        return gamesRepository.findAll();
    }

    @Override
    public VideoGame getVideoGame(HttpServletRequest request, String gameId) {
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(gameId)){
            throw new BadRequestException("Invalid Id: " + gameId);
        }

        //Convert movieId to type ObjectId
        ObjectId objectId = new ObjectId(gameId);

        if(!gamesRepository.existsById(objectId)){
            throw new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist");
        }

        //Retrieve user id from access token
        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String userId = principal.getAccount().getKeycloakSecurityContext().getToken().getSubject();

        //Store user id to database
        User user = new User();
        user.setUserId(userId);
        user.setGamesPlayed(1);
        usersService.createUser(user);

        return gamesRepository.findById(objectId).orElseThrow(() -> new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist"));
    }

    @Override
    public VideoGame putVideoGame(String gameId, VideoGame videoGame) {
        if(!ObjectId.isValid(gameId)){
            throw new BadRequestException("Invalid Id: " + gameId);
        }

        ObjectId objectId = new ObjectId(gameId);
        videoGame.setGameId(objectId);

        return gamesRepository.save(videoGame);
    }

    @Override
    public VideoGame patchVideoGame(String gameId, VideoGame videoGame) {
        if(!ObjectId.isValid(gameId)){
            throw new BadRequestException("Invalid Id: " + gameId);
        }

        ObjectId objectId = new ObjectId(gameId);

        VideoGame existingGame = gamesRepository.findById(objectId).get();

        //region field updaters
        if(videoGame.getGameName() != null){
            existingGame.setGameName(videoGame.getGameName());
        }

        if(videoGame.getGameDescription() != null){
            existingGame.setGameDescription(videoGame.getGameDescription());
        }

        if(videoGame.getDateReleased() != null){
            existingGame.setDateReleased(videoGame.getDateReleased());
        }

        if(videoGame.getComposer() != null){
            existingGame.setComposer(videoGame.getComposer());
        }

        if(videoGame.getDateReleased() != null){
            existingGame.setDateReleased(videoGame.getDateReleased());
        }

        if(videoGame.getPublisher() != null){
            existingGame.setPublisher(videoGame.getPublisher());
        }

        if(videoGame.getDeveloper() != null){
            existingGame.setDeveloper(videoGame.getDeveloper());
        }

        if(videoGame.getComposer() != null){
            existingGame.setComposer(videoGame.getComposer());
        }

        if(videoGame.getCoverArtLink() != null){
            existingGame.setCoverArtLink(videoGame.getCoverArtLink());
        }

        if(videoGame.getGenre() != null){
            existingGame.setGenre(videoGame.getGenre());
        }

        if(videoGame.getModes() != null){
            existingGame.setModes(videoGame.getModes());
        }

        if(videoGame.getEngine() != null){
            existingGame.setEngine(videoGame.getEngine());
        }

        if(videoGame.getWriter() != null){
            existingGame.setWriter(videoGame.getWriter());
        }
        //endregion

        return gamesRepository.save(existingGame);
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
