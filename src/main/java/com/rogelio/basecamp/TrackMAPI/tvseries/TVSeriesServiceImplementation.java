package com.rogelio.basecamp.TrackMAPI.tvseries;

import com.rogelio.basecamp.TrackMAPI.errorhandling.BadRequestException;
import com.rogelio.basecamp.TrackMAPI.errorhandling.RecordNotFoundException;
import com.rogelio.basecamp.TrackMAPI.movie.Movie;
import com.rogelio.basecamp.TrackMAPI.user.User;
import com.rogelio.basecamp.TrackMAPI.user.UsersRepository;
import com.rogelio.basecamp.TrackMAPI.user.UsersService;
import org.bson.types.ObjectId;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TVSeriesServiceImplementation implements TVSeriesService{

    @Autowired
    private TVSeriesRepository tvSeriesRepository;

    @Autowired
    private UsersService usersService;

    @Override
    public TVSeries createTVSeries(TVSeries tvSeries) {
        return tvSeriesRepository.save(tvSeries);
    }

    @Override
    public List<TVSeries> getAllTVSeries() {
        return tvSeriesRepository.findAll();
    }

    @Override
    public TVSeries getTVSeries(HttpServletRequest request, String tvSerId) {
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(tvSerId)){
            throw new BadRequestException("Invalid Id: " + tvSerId);
        }

        //Convert movieId to type ObjectId
        ObjectId objectId = new ObjectId(tvSerId);

        if(!tvSeriesRepository.existsById(objectId)){
            throw new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist");
        }

        //Retrieve user id from access token
        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String userId = principal.getAccount().getKeycloakSecurityContext().getToken().getSubject();


        //Store user id to database
        User user = new User();
        user.setUserId(userId);
        user.setTvSeriesWatched(1);
        usersService.createUser(user);

        return tvSeriesRepository.findById(objectId)
                .orElseThrow(() -> new RecordNotFoundException("Can't find " + objectId.toHexString() + ". It does not exist"));
    }


    @Override
    public TVSeries putTVSeries(String tvSerId, TVSeries tvSeries) {
        if(!ObjectId.isValid(tvSerId)){
            throw new BadRequestException("Invalid Id: " + tvSerId);
        }

        ObjectId objectId = new ObjectId(tvSerId);
        tvSeries.setTvSerId(objectId);

        return tvSeriesRepository.save(tvSeries);
    }

    @Override
    public TVSeries patchTVSeries(String tvSerId, TVSeries tvSeries) {
        if(!ObjectId.isValid(tvSerId)){
            throw new BadRequestException("Invalid Id: " + tvSerId);
        }

        ObjectId objectId = new ObjectId(tvSerId);
        TVSeries existingSeries = tvSeriesRepository.findById(objectId).get();

        //region field updaters
        if(tvSeries.getSeriesName() != null){
            existingSeries.setSeriesName(tvSeries.getSeriesName());
        }

        if(tvSeries.getSeriesDescription() != null){
            existingSeries.setSeriesDescription(tvSeries.getSeriesDescription());
        }

        if(tvSeries.getDirector() != null){
            existingSeries.setDirector(tvSeries.getDirector());
        }

        if(tvSeries.getGenre() != null){
            existingSeries.setGenre(tvSeries.getGenre());
        }

        if(tvSeries.getCreatedBy() != null){
            existingSeries.setCreatedBy(tvSeries.getCreatedBy());
        }

        if(tvSeries.getComposer() != null){
            existingSeries.setComposer(tvSeries.getComposer());
        }

        if(tvSeries.getNumberOfSeasons() != 0){
            existingSeries.setNumberOfSeasons(tvSeries.getNumberOfSeasons());
        }

        if(tvSeries.getNumOfEpisodes() != 0){
            existingSeries.setNumOfEpisodes(tvSeries.getNumOfEpisodes());
        }

        if(tvSeries.getCoverArtLink() != null){
            existingSeries.setCoverArtLink(tvSeries.getCoverArtLink());
        }

        if(tvSeries.getProductionCompany() != null){
            existingSeries.setProductionCompany(tvSeries.getProductionCompany());
        }

        if(tvSeries.getDistributer() != null){
            existingSeries.setDistributer(tvSeries.getDistributer());
        }

        if(tvSeries.getRunningTime() != null){
            existingSeries.setRunningTime(tvSeries.getRunningTime());
        }

        if(tvSeries.getActors() != null){
            existingSeries.setActors(tvSeries.getActors());
        }
        //endregion

        return tvSeriesRepository.save(existingSeries);
    }

    @Override
    public String deleteTVSeries(String tvSerId) {
        //if hex string is invalid, throws bad syntax exception
        if(!ObjectId.isValid(tvSerId)){
            throw new BadRequestException("Invalid Id: " + tvSerId);
        }

        ObjectId objectId = new ObjectId(tvSerId);

        tvSeriesRepository.deleteById(objectId);
        return "Successfully deleted TV Series";
    }

    @Override
    public String deleteAllTVSeries() {
        tvSeriesRepository.deleteAll();
        return "Successfully deleted all TV Series";
    }
}
