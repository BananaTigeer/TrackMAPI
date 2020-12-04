package com.rogelio.basecamp.TrackMAPI.tvseries;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;


@Document(collection = "tv-series")
public class TVSeries {

    @Id
    private ObjectId tvSerId;

    @NotNull
    private String seriesName;

    @NotNull
    private String seriesDescription;

    @NotNull
    private String director;

    @NotNull
    private List<String> genre;

    @NotNull
    private List<String> createdBy;

    @NotNull
    private String composer;

    @NotNull
    private int numberOfSeasons;

    @NotNull
    private int numOfEpisodes;

    @NotNull
    private String coverArtLink;

    @NotNull
    private List<String> productionCompany;

    @NotNull
    private List<String> distributer;

    @NotNull
    private String runningTime;

    @NotNull
    private List<String> actors;

    public TVSeries(){}

    /*
    public TVSeries(String seriesName, String seriesDescription, String director) {
        this.seriesName = seriesName;
        this.seriesDescription = seriesDescription;
        this.director = director;
    }

     */

    /*
    public TVSeries(
            String seriesName,
            String seriesDescription,
            List<String> genre,
            List<String> createdBy,
            String composer,
            int numOfSeasons,
            int numOfEpisodes,
            String coverArtLink,
            List<String> productionCompany,
            List<String> distributer,
            String runningTime,
            List<String> actors){

        this.seriesName = seriesName;
        this.seriesDescription = seriesDescription;
        this.genre = genre;
        this.createdBy = createdBy;
        this.composer = composer;
        this.numberOfSeasons = numOfSeasons;
        this.numOfEpisodes = numOfEpisodes;
        this.coverArtLink = coverArtLink;
        this.productionCompany = productionCompany;
        this.distributer = distributer;
        this.runningTime = runningTime;
        this.actors = actors;

    }

     */

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

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public List<String> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<String> createdBy) {
        this.createdBy = createdBy;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumOfEpisodes() {
        return numOfEpisodes;
    }

    public void setNumOfEpisodes(int numOfEpisodes) {
        this.numOfEpisodes = numOfEpisodes;
    }

    public String getCoverArtLink() {
        return coverArtLink;
    }

    public void setCoverArtLink(String coverArtLink) {
        this.coverArtLink = coverArtLink;
    }

    public List<String> getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(List<String> productionCompany) {
        this.productionCompany = productionCompany;
    }

    public List<String> getDistributer() {
        return distributer;
    }

    public void setDistributer(List<String> distributer) {
        this.distributer = distributer;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    //endregion
}
