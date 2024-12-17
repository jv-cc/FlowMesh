package com.jv_cc.flowmesh.hub_server.application.exception;

import org.springframework.http.HttpStatus;

public class MasterOnlyAccessException extends HubException{
    public MasterOnlyAccessException() {
        super(Error.MASTER_ONLY_ACCESS, HttpStatus.FORBIDDEN);
    }
}
