package com.jv_cc.flowmesh.hub_server.application.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    INTERNAL_SERVER_ERROR(9999, "서버 오류입니다.");

    final Integer code;

    final String message;
}


