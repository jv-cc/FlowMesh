package com.jv_cc.flowmesh.deliverymanager.application.exception;

import org.springframework.http.HttpStatus;

public class ExceededCapacityException extends DeliveryManagerException {
    public ExceededCapacityException() {
        super(Error.EXCEEDED_CAPACITY, HttpStatus.BAD_REQUEST);
    }
}
