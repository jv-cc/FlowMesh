package com.jv_cc.flowmesh.auth.application.exception;

import org.springframework.http.HttpStatus;

public class UserExistEmailException extends AuthException {
    public UserExistEmailException() {
        super(Error.USER_EXIST_EMAIL, HttpStatus.BAD_REQUEST);
    }
}