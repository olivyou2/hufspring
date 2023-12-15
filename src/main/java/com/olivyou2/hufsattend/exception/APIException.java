package com.olivyou2.hufsattend.exception;

import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.HashMap;
import java.util.Map;

@Setter
public class APIException extends RuntimeException{
    public HttpStatusCode httpCode;
    public String message;
    public String solution;

    public Map<String, String> getErrorPayload(){
        Map<String, String> errorPayload = new HashMap<>();

        errorPayload.put("httpCode", httpCode.toString());
        errorPayload.put("message", message);

        if (solution != null){
            errorPayload.put("solution", solution);
        }

        return  errorPayload;
    }
}
