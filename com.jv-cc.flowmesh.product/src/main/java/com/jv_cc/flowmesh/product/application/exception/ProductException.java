package com.jv_cc.flowmesh.product.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProductException extends RuntimeException {

    Error error;

    HttpStatus httpStatus;

    public ProductException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
