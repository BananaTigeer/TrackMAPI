package com.rogelio.basecamp.TrackMAPI.Repository;

import com.rogelio.basecamp.TrackMAPI.Models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
}
