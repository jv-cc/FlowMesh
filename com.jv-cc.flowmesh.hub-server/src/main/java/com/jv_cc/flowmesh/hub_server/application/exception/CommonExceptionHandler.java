package com.jv_cc.flowmesh.hub_server.application.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Hidden
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(HubException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> HubExceptionHandler(HubException e) {

        Error error = e.getError();

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(Map.of(
                        "code", error.getCode(),
                        "message", error.getMessage()
                ));
    }

}
