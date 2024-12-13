package com.jv_cc.flowmesh.auth.application.exception;

import org.springframework.http.HttpStatus;

public class UserExistNicknameException extends AuthException {
    public UserExistNicknameException() {
        super(Error.USER_EXIST_NICKNAME, HttpStatus.BAD_REQUEST);
    }
}