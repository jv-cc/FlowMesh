package com.jv_cc.flowmesh.deliverymanager.application.exception;

import org.springframework.http.HttpStatus;

public class NotFoundDeliveryManagerException extends DeliveryManagerException {
    public NotFoundDeliveryManagerException() {
        super(Error.NOT_FOUND_DELIVERY_MANAGER, HttpStatus.BAD_REQUEST);
    }
}
