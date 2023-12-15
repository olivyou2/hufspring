package com.olivyou2.hufsattend.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivyou2.hufsattend.exception.APIException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionAdvice {
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> process(APIException exception) throws JsonProcessingException {
        return ResponseEntity
                .status(exception.httpCode)
                .body(exception.getErrorPayload());
    }
}
