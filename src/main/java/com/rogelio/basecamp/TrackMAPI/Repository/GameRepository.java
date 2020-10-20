package com.rogelio.basecamp.TrackMAPI.Repository;

import com.rogelio.basecamp.TrackMAPI.Models.Games;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Games, ObjectId> {
}
