package com.jv_cc.flowmesh.hub_server.application.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice(basePackages = {"com.jv-cc.flowmesh.hub_server"})
public class CommonExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HubException.class)
    public ResponseEntity<Object> ReviewExceptionHandler(HubException e) {

        Error error = e.getError();

        return ResponseEntity
                .status(error.getCode())
                .body(error.getMessage());
    }

}
