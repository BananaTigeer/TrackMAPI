package com.rogelio.basecamp.TrackMAPI.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    public BadRequestException(){
        super("default");
    }
    public BadRequestException(String exception){
        super(exception);
    }
}
