package com.jv_cc.flowmesh.hub_server.application.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {
    MASTER_ONLY_ACCESS(1000, "마스터 권한만 접근 가능합니다."),

    DUPLICATE_HUB_NAME(4200, "중복된 허브 이름이 존재합니다."),
    DUPLICATE_HUB_COORDINATES(4201, "중복된 허브 좌표가 존재합니다."),

    NOT_FOUNT_HUB(4100, "해당 허브가 존재하지 않습니다."),

    INTERNAL_SERVER_ERROR(9999, "서버 오류입니다.");

    final Integer code;

    final String message;
}


