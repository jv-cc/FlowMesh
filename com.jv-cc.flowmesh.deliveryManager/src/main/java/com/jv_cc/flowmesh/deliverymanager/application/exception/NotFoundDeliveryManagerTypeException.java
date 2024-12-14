package com.jv_cc.flowmesh.deliverymanager.application.exception;

import org.springframework.http.HttpStatus;

public class NotFoundDeliveryManagerTypeException extends DeliveryManagerException {
    public NotFoundDeliveryManagerTypeException() {
        super(Error.NOT_FOUND_DELIVERY_MANAGER_TYPE, HttpStatus.NOT_FOUND);
    }

}
