package com.jv_cc.flowmesh.company.application.exception.hub;

import com.jv_cc.flowmesh.company.application.exception.Error;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HubException extends RuntimeException {

    com.jv_cc.flowmesh.company.application.exception.Error error;

    HttpStatus httpStatus;

    public HubException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
