package com.olivyou2.hufsattend.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationNotSuggestedException extends APIException{
    public AuthorizationNotSuggestedException() {
        this.setHttpCode(HttpStatus.BAD_REQUEST);
        this.setMessage("AuthorizationNotSuggested");
        this.setSolution("You should set Authorization header");
    }
}
