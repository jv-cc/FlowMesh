package com.jv_cc.flowmesh.auth.application.exception;

import org.springframework.http.HttpStatus;

public class UserExistSlackidException extends AuthException {
    public UserExistSlackidException() {
        super(Error.USER_EXIST_SLACKID, HttpStatus.BAD_REQUEST);
    }
}