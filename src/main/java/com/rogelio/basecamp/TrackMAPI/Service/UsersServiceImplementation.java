package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.User;
import com.rogelio.basecamp.TrackMAPI.Repository.UsersRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImplementation implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    public UsersServiceImplementation(){}

    @Override
    public void createUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<User> getUser(ObjectId userId) {
        return usersRepository.findById(userId);
    }

    @Override
    public User putUser(ObjectId userId, User user) {
        user.setUserId(userId);
        return usersRepository.save(user);
    }

    @Override
    public User patchUser(ObjectId userId, User user) {
        user.setUserId(userId);
        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(ObjectId userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUsers() {
        usersRepository.deleteAll();
    }
}
