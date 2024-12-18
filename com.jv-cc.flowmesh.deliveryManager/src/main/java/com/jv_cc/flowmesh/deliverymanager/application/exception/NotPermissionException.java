package com.jv_cc.flowmesh.deliverymanager.application.exception;

import org.springframework.http.HttpStatus;

public class NotPermissionException extends DeliveryManagerException{
    public NotPermissionException() {
        super(Error.NOT_PERMISSION, HttpStatus.FORBIDDEN);
    }
}
