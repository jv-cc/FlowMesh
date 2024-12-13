package com.jv_cc.flowmesh.hub_server.application.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(HubException.class)
    public ResponseEntity<Object> ReviewExceptionHandler(HubException e) {

        Error error = e.getError();

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(Map.of(
                        "code", error.getCode(),
                        "message", error.getMessage()
                ));
    }

}
