package com.jv_cc.flowmesh.order_service.application.exception;

import org.springframework.http.HttpStatus;

public class OrderNotChangedException extends OrderException {
    public OrderNotChangedException() {
        super(Error.ORDER_NOT_CHANGED, HttpStatus.BAD_REQUEST);
    }
}
