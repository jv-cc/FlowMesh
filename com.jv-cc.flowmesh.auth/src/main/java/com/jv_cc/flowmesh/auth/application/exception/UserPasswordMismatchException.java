package com.jv_cc.flowmesh.auth.application.exception;

import org.springframework.http.HttpStatus;

public class UserPasswordMismatchException extends AuthException {
    public UserPasswordMismatchException() {
        super(Error.USER_PASSWORD_MISMATCH, HttpStatus.UNAUTHORIZED);
    }
}