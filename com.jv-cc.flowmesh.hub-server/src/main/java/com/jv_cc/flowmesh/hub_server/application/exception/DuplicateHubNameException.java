package com.jv_cc.flowmesh.hub_server.application.exception;

import org.springframework.http.HttpStatus;

public class DuplicateHubNameException extends HubException {

    public DuplicateHubNameException() {
        super(Error.DUPLICATE_HUB_NAME, HttpStatus.BAD_REQUEST);
    }

}
