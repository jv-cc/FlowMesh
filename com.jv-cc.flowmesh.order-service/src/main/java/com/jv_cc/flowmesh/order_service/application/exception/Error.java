package com.jv_cc.flowmesh.order_service.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    INTERNAL_SERVER_ERROR(9999, "서버 오류입니다.");

    private Integer code;
    private String message;

}
