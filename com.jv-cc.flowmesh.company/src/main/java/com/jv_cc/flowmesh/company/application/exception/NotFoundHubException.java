package com.jv_cc.flowmesh.company.application.exception;

import org.springframework.http.HttpStatus;

public class NotFoundHubException extends HubException {
    public NotFoundHubException() {
        super(Error.NOT_FOUND_HUB, HttpStatus.NOT_FOUND);
    }
}
