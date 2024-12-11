package com.jv_cc.flowmesh.auth.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class AuthException extends RuntimeException {

    private final Error error;

    private final HttpStatus httpStatus;

    public AuthException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
