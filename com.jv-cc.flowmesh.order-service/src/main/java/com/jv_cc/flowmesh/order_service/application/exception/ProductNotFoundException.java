package com.jv_cc.flowmesh.order_service.application.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends OrderException {
    public ProductNotFoundException() {
        super(Error.PRODUCT_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }
}
