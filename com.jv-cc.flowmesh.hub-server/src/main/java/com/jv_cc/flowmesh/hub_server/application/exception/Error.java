package com.jv_cc.flowmesh.hub_server.application.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    DUPLICATE_HUB_NAME(4200, "중복된 허브 이름이 존재합니다." ),
    DUPLICATE_HUB_COORDINATES(4201, "중복된 허브 좌표가 존재합니다."),

    INTERNAL_SERVER_ERROR(9999, "서버 오류입니다.");

    final Integer code;

    final String message;
}


