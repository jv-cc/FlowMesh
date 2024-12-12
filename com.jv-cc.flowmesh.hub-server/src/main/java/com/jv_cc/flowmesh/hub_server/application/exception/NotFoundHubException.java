package com.jv_cc.flowmesh.hub_server.application.exception;

import org.springframework.http.HttpStatus;

public class NotFoundHubException extends HubException{

    public NotFoundHubException() {
        super(Error.NOT_FOUNT_HUB, HttpStatus.NOT_FOUND);
    }
}
