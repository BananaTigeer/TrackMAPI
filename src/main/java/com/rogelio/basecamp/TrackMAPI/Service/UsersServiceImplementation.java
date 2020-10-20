package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.User;
import com.rogelio.basecamp.TrackMAPI.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImplementation implements UsersService{

    @Autowired
    private UserRepository userRepository;

    public UsersServiceImplementation(){}

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(ObjectId userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User putUser(ObjectId userId, User user) {
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public User patchUser(ObjectId userId, User user) {
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(ObjectId userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
