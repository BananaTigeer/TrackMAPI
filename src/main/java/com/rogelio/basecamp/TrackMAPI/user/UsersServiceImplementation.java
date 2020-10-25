package com.rogelio.basecamp.TrackMAPI.user;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImplementation implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    public UsersServiceImplementation(){}

    @Override
    public User createUser(User user) {
        return usersRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(userId)){
            throw new BadRequestException("Invalid Id: " + userId);
        }

        ObjectId objectId = new ObjectId(userId);

        return usersRepository.findById(objectId).orElseThrow(() -> new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist"));
    }

    @Override
    public User updateUser(String userId, User user) {
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(userId)){
            throw new BadRequestException("Invalid Id: " + userId);
        }

        ObjectId objectId = new ObjectId(userId);

        if(!usersRepository.existsById(objectId)){
            throw new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist");
        }

        user.setUserId(objectId);
        return usersRepository.save(user);
    }

    @Override
    public String deleteUser(String userId) {
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(userId)){
            throw new BadRequestException("Invalid Id: " + userId);
        }

        ObjectId objectId = new ObjectId(userId);

        usersRepository.deleteById(objectId);
        return "Successfully deleted user";
    }

    @Override
    public String deleteAllUsers() {
        usersRepository.deleteAll();
        return "Successfully deleted all users";
    }

}
