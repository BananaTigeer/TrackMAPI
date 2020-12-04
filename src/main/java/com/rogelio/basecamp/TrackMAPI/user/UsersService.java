package com.rogelio.basecamp.TrackMAPI.user;

import org.bson.types.ObjectId;

import java.util.List;

public interface UsersService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);
    User putUser(String userId, User user);
    User patchUser(String userId, User user);
    String deleteUser(String userId);
    String deleteAllUsers();
}
