package com.rogelio.basecamp.TrackMAPI.user;

import org.bson.types.ObjectId;

import java.util.List;

public interface UsersService {
    void createUser(User user);
    List<User> getAllUsers();
    User getUser(ObjectId userId);
    User updateUser(ObjectId userId, User user);
    void deleteUser(ObjectId userId);
    void deleteAllUsers();
}
