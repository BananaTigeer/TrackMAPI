package com.rogelio.basecamp.TrackMAPI.Repository;

import com.rogelio.basecamp.TrackMAPI.Models.VideoGame;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends MongoRepository<VideoGame, ObjectId> {
}
