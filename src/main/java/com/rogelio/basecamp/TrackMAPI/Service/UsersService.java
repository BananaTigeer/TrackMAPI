package com.rogelio.basecamp.TrackMAPI.Service;

import com.rogelio.basecamp.TrackMAPI.Models.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    abstract void createUser(User user);
    abstract List<User> getAllUsers();
    abstract Optional<User> getUser(ObjectId userId);
    abstract User putUser(ObjectId userId, User user);
    abstract User patchUser(ObjectId userId, User user);
    abstract void deleteUser(ObjectId userId);
    abstract void deleteAllUsers();
}
