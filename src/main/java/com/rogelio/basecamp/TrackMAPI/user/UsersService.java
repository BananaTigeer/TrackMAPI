package com.rogelio.basecamp.TrackMAPI.user;

import org.bson.types.ObjectId;

import java.util.List;

public interface UsersService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);
    User updateUser(String userId, User user);
    String deleteUser(String userId);
    String deleteAllUsers();
}
