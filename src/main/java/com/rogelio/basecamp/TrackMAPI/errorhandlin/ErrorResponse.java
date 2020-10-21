package com.rogelio.basecamp.TrackMAPI.errorhandlin;

import java.util.List;

public class ErrorResponse {
    private String message;
    private List<String> details;

    public ErrorResponse(String message, List<String> details){
        super();
        this.message = message;
        this.details = details;
    }

    //region Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
    //endregion
}
