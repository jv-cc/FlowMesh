package com.jv_cc.flowmesh.deliverymanager.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DeliveryManagerException extends RuntimeException {
    Error error;
    HttpStatus httpStatus;

    public DeliveryManagerException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
