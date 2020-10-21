package com.rogelio.basecamp.TrackMAPI.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadSyntaxException extends RuntimeException{
    public BadSyntaxException(String exception){
        super(exception);
    }
}
