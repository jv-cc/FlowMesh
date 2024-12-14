package com.jv_cc.flowmesh.product.application.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Object> ProductExceptionHandler(ProductException e) {

        Error error = e.getError();

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(Map.of(
                        "code", error.getCode(),
                        "message", error.getMessage()
                ));
    }

}
