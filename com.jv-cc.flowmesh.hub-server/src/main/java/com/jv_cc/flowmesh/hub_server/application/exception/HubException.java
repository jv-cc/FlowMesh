package com.jv_cc.flowmesh.hub_server.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HubException extends RuntimeException {

    Error error;

    HttpStatus httpStatus;

    public HubException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
