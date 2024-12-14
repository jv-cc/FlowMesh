package com.jv_cc.flowmesh.product.application.exception;

import com.jv_cc.flowmesh.product.application.exception.company.CompanyException;
import com.jv_cc.flowmesh.product.application.exception.hub.HubException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(HubException.class)
    public ResponseEntity<Object> HubExceptionHandler(HubException e) {

        Error error = e.getError();

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(Map.of(
                        "code", error.getCode(),
                        "message", error.getMessage()
                ));
    }

    @ExceptionHandler(CompanyException.class)
    public ResponseEntity<Object> CompanyExceptionHandler(CompanyException e) {

        Error error = e.getError();

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(Map.of(
                        "code", error.getCode(),
                        "message", error.getMessage()
                ));
    }

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
