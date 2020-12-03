package com.rogelio.basecamp.TrackMAPI.user;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImplementation implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User createUser(User user) {

        if(usersRepository.existsById(user.getUserId())){
            //Update if id exists
            return updateUser(user.getUserId(), user);
        }else{
            //Create new record if not
            return usersRepository.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new RecordNotFoundException("Can't find " + userId + ". It does not exist"));
    }

    @Override
    public User updateUser(String userId, User user) {
        User existingUser = usersRepository.findById(userId).get();
        existingUser.setMoviesWatched(existingUser.getMoviesWatched() + user.getMoviesWatched());
        existingUser.setGamesPlayed(existingUser.getGamesPlayed() + user.getGamesPlayed());
        existingUser.setTvSeriesWatched(existingUser.getTvSeriesWatched() + user.getTvSeriesWatched());

        return usersRepository.save(existingUser);
    }


    @Override
    public String deleteUser(String userId) {
        usersRepository.deleteById(userId);
        return "Successfully deleted user";

    }

    @Override
    public String deleteAllUsers() {
        usersRepository.deleteAll();
        return "Successfully deleted all users";
    }

}
