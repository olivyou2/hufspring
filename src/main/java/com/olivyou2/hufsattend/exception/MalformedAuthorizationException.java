package com.olivyou2.hufsattend.exception;

import org.springframework.http.HttpStatus;

public class MalformedAuthorizationException extends APIException{
    public MalformedAuthorizationException() {

        this.setHttpCode(HttpStatus.BAD_REQUEST);
        this.setMessage("MalformedAuthorization");
        this.setSolution("You should set authorization header as ID:PASSWORD");
    }
}
