package com.jv_cc.flowmesh.delivery.application.exception;

import org.springframework.http.HttpStatus;

public class NotDeletedException extends DeliveryException {
    public NotDeletedException() {
        super(Error.NOT_DELETED_DELIVERY, HttpStatus.BAD_REQUEST);
    }
}
