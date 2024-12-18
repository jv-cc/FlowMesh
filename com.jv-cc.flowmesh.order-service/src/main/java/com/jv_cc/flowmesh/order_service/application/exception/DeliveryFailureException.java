package com.jv_cc.flowmesh.order_service.application.exception;

import org.springframework.http.HttpStatus;

public class DeliveryFailureException extends OrderException{
    public DeliveryFailureException() {
        super(Error.DELIVERY_FAILURE, HttpStatus.PRECONDITION_FAILED);
    }
}
