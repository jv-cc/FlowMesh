package com.jv_cc.flowmesh.hub_server.application.exception;

import org.springframework.http.HttpStatus;

public class DuplicateHubCoordinatesException extends HubException {

    public DuplicateHubCoordinatesException() {
        super(Error.DUPLICATE_HUB_COORDINATES, HttpStatus.BAD_REQUEST);
    }
}
