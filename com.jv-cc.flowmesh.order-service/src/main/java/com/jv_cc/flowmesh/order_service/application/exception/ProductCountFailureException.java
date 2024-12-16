package com.jv_cc.flowmesh.order_service.application.exception;

import org.springframework.http.HttpStatus;

public class ProductCountFailureException extends OrderException{
    public ProductCountFailureException() {
        super(Error.PRODUCT_FAILURE, HttpStatus.PRECONDITION_FAILED);
    }
}
