package com.jv_cc.flowmesh.company.application.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    NOT_FOUND_HUB(4000, "해당 허브가 존재하지 않습니다."),

    NOT_FOUND_COMPANY(5000, "해당 업체가 존재하지 않습니다."),
    DUPLICATE_COMPANY_NAME(5100, "중복된 업체 이름이 존재합니다."),

    INTERNAL_SERVER_ERROR(9999, "서버 오류입니다.");

    final Integer code;

    final String message;
}


