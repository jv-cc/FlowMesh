package com.jv_cc.flowmesh.delivery.application.exception;

import org.springframework.http.HttpStatus;

public class NotCreatedDeliveryException extends DeliveryException {
    public NotCreatedDeliveryException() {
        super(Error.NOT_CREATED_DELIVERY, HttpStatus.NOT_ACCEPTABLE);
    }
}
