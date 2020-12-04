package com.rogelio.basecamp.TrackMAPI.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends RuntimeException{
    public MethodNotAllowedException(){
        super();
    }
    public MethodNotAllowedException(String exception){
        super(exception);
    }
}
