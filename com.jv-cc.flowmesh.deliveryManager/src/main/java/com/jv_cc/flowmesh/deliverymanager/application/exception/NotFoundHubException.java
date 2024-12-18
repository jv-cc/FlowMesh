package com.jv_cc.flowmesh.deliverymanager.application.exception;

import org.springframework.http.HttpStatus;

public class NotFoundHubException extends DeliveryManagerException {

    public NotFoundHubException() {
        super(Error.NOT_FOUND_HUB, HttpStatus.BAD_REQUEST);
    }
}
