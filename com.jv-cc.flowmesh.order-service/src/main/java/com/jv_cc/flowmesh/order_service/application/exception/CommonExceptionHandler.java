package com.jv_cc.flowmesh.order_service.application.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> OrderExceptionHandler(OrderException e) {
        Error error = e.getError();

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(
                        Map.of(
                                "code", error.getCode(),
                                "message", error.getMessage()
                        )
                );
    }
}
