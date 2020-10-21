package com.rogelio.basecamp.TrackMAPI.tvseries;

import com.rogelio.basecamp.TrackMAPI.tvseries.TVSeries;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVSeriesRepository extends MongoRepository<TVSeries, ObjectId> {
}
