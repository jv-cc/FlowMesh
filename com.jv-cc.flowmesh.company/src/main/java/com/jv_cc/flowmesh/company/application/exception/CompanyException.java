package com.jv_cc.flowmesh.company.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CompanyException extends RuntimeException {

    Error error;

    HttpStatus httpStatus;

    public CompanyException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
