package com.jv_cc.flowmesh.deliverymanager.application.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    INTERNAL_SERVER_ERROR(9999, "서버 오류입니다."),

    NOT_PERMISSION(1180, "권한이 없습니다."),

    NOT_FOUND_HUB(4000, "해당 허브가 존재하지 않습니다."),

    NOT_FOUND_DELIVERY_MANAGER_TYPE(9101, "해당 배송 담당자 타입이 존재하지 않습니다."),
    EXCEEDED_CAPACITY(9102, "정원이 초과되었습니다.")
    ;

    private Integer code;
    private String message;
}
