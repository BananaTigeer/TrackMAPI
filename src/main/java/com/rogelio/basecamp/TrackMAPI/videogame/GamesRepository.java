package com.rogelio.basecamp.TrackMAPI.videogame;

import com.rogelio.basecamp.TrackMAPI.videogame.VideoGame;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends MongoRepository<VideoGame, ObjectId> {
}
