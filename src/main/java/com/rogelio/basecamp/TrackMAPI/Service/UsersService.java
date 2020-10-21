package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    void createUser(User user);
    List<User> getAllUsers();
    User getUser(ObjectId userId);
    User updateUser(ObjectId userId, User user);
    void deleteUser(ObjectId userId);
    void deleteAllUsers();
}
