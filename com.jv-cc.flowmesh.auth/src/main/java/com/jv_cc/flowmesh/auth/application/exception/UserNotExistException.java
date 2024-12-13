package com.jv_cc.flowmesh.auth.application.exception;

import org.springframework.http.HttpStatus;

public class UserNotExistException extends AuthException {
    public UserNotExistException() {
        super(Error.USER_NOT_EXIST, HttpStatus.NOT_FOUND);
    }
}