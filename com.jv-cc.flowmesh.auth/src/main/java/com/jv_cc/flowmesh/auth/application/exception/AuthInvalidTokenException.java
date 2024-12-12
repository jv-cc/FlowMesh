package com.jv_cc.flowmesh.auth.application.exception;

import org.springframework.http.HttpStatus;

public class AuthInvalidTokenException extends AuthException {
     public AuthInvalidTokenException() {
          super(Error.AUTH_INVALID_TOKEN, HttpStatus.BAD_REQUEST);
     }
}
