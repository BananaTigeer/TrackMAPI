package com.rogelio.basecamp.TrackMAPI.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "tv-series")
public class TVSeries {

    @Id
    private ObjectId tvSerId;

    private String seriesName;
    private String seriesDescription;
    private String director;

    public TVSeries(String seriesName, String seriesDescription, String director) {
        this.seriesName = seriesName;
        this.seriesDescription = seriesDescription;
        this.director = director;
    }

    //region Getters and Setters

    public String getTvSerId() {
        return tvSerId.toHexString();
    }

    public void setTvSerId(ObjectId tvSerId) {
        this.tvSerId = tvSerId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesDescription() {
        return seriesDescription;
    }

    public void setSeriesDescription(String seriesDescription) {
        this.seriesDescription = seriesDescription;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    //endregion
}
