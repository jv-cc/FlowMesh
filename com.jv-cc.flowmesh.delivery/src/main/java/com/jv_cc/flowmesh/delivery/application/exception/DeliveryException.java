package com.jv_cc.flowmesh.delivery.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DeliveryException extends RuntimeException {
    Error error;
    HttpStatus httpStatus;

    public DeliveryException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
