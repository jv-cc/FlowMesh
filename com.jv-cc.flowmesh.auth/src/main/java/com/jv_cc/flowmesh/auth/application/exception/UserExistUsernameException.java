package com.jv_cc.flowmesh.auth.application.exception;

import org.springframework.http.HttpStatus;

public class UserExistUsernameException extends AuthException {
    public UserExistUsernameException() {
        super(Error.USER_EXIST_USERNAME, HttpStatus.BAD_REQUEST);
    }
}