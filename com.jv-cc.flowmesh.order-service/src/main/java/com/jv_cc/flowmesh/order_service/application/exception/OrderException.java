package com.jv_cc.flowmesh.order_service.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OrderException extends RuntimeException {
    Error error;
    HttpStatus httpStatus;

    public OrderException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
